package com.bezkoder.springjwt
.service.filter;

import java.util.List;
import java.util.TreeSet;

import com.bezkoder.springjwt.models
.TemporalExtraction;
import com.bezkoder.springjwt
.exceptions.ExceptionMessages;
import com.bezkoder.springjwt
.temporal.entities.TimeDate;
import org.joda.time.LocalDateTime;

import com.bezkoder.springjwt.models
.Settings;
import com.bezkoder.springjwt
.temporal.entities.Temporal;
import com.bezkoder.springjwt
.temporal.entities.Type;
import com.bezkoder.springjwt
.utils.Utils;

/**
 * <h1>Filter Rules Service Class</h1> is used for filtering extracted time
 * expressions from simple Rules(like timezone or word 'every', or words that
 * explicitly don't belong to time expressions), or past dates (if such settings
 * were previously set by user)
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */

public class FilterRulesService {

    /**
     * Main filtering method: exludes results by rule type filtering, simple
     * rules.txt, lates dates, etc.
     */
    public TreeSet<TemporalExtraction> removeSimpleTemporals (List<TemporalExtraction> list, Settings settings){
        for (int i = 0; i < list.size(); i++) {
            boolean excludeSimpleCases = false;
            boolean excludePastDates = false;
            boolean excludeByRule = false;
            boolean excludeFilterRules = false;
            boolean includeByRule = false;

            TemporalExtraction current = list.get(i);
            List<Temporal> temporals = current.getTemporal();
            if (temporals != null && temporals.get(0) != null && temporals.get(0).getType() != null) {
                excludeByRule = excludeRulesSelectedByUsers(current, settings);
                includeByRule = includeRulesSelectedByUsers(current, settings);
                excludeSimpleCases = filterSimpleCases(current);
                excludeFilterRules = excludeFilterRules(current);
                if (settings.isIncludeOnlyLatestDates()) {
                    excludePastDates = excludePastDates(current, settings);
                }
                if(settings.isUserIncludeRuleExist()) {
                    if (excludeSimpleCases || excludePastDates || excludeByRule || excludeFilterRules || !(includeByRule)) {
                        list.remove(i);
                        i = i - 1;
                    }
                }
                else{
                    if (excludeSimpleCases || excludePastDates || excludeByRule || excludeFilterRules) {
                        list.remove(i);
                        i = i - 1;
                    }
                }
            }
        }
        return new TreeSet<TemporalExtraction>(list);
    }

    /**
     * Method checks whether current time found expression needs to be excluded
     * by user set rule type
     * @return boolean
     */
    private boolean excludeRulesSelectedByUsers(TemporalExtraction current, Settings settings) {
        if (settings.getRulesToIgnore().contains(current.getRule().getId())) {
            return true;
        }
        return false;
    }

    private boolean includeRulesSelectedByUsers(TemporalExtraction current, Settings settings){
        if(settings.getRulesToInclude().contains(current.getRule().getId())) {
            return true;
        }
        return false;
    }
    /**
     * Method checks whether current time found expression needs to be excluded
     * because of not combined simple rule, like timezone or word 'every'
     */
    private boolean filterSimpleCases(TemporalExtraction current) {
        Type currentType = current.getTemporal().get(0).getType();
        if (currentType == Type.TIMEZONE || currentType == Type.EVERY || current.getClassOfRuleType().equals("MonthAndDayRule5")) {
            return true;
        }
        return false;
    }

    /**
     * Method checks whether current time found expression needs to be excluded
     * because of the filtering rule
     */
    private boolean excludeFilterRules(TemporalExtraction current) {
        Type currentType = current.getTemporal().get(0).getType();
        if (currentType == Type.Filter) {
            return true;
        }
        return false;

    }

    /**
     * Method checks whether current time found expression needs to be excluded
     * by user set to find only dates that are current date or after current
     * date
     */

    private boolean excludePastDates (TemporalExtraction current, Settings settings) {
        TimeDate timeDate = current.getTemporal().get(0).getEndDate();
        if (timeDate == null) {
            timeDate = current.getTemporal().get(0).getStartDate();
        }

        LocalDateTime foundDateTime = Utils.getTimeDate(timeDate, settings.getDate());
        if (foundDateTime.isBefore(settings.getDate())) {
            return true;
        }
        return false;
    }

}
