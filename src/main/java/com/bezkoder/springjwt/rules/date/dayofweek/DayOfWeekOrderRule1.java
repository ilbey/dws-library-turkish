package com.bezkoder.springjwt.rules.date.dayofweek;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.temporal.entities.WeekOfMonth;
import com.bezkoder.springjwt.utils.Utils;

// the first Tuesday (of the month)
public class DayOfWeekOrderRule1 extends ExtractionRule {

    protected double confidence = 0.9;
    private String ruleEn = "\\b((the[\\s]*)?(" + TemporalConstants.BASIC_ORDER + ")[\\s]*(" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")"+ "[s]?)([\\s]*(of)[\\s]*(the[\\s]*)?(month))?)\\b";
            
    //private String rule = "\\b((ayın[\\s]*)?(" + TemporalConstants.BASIC_ORDER + ")[\\s]*(" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")"+ ")(sı|si|ı)*))\\b";
    
    private String rule = "\\b((ayın[\\s]*)?(" + TemporalConstants.BASIC_ORDER + ")[\\s]*(" + "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")"+ "))\\b";
    private int priority = 3;
    protected String example = "the first Tuesday, the third Monday of the month, the last Friday";
    protected UUID id = UUID.fromString("1fde55f7-8fee-485f-8b26-50d60536e528");
    protected String ruleType = "";
    public DayOfWeekOrderRule1() {
    }

    public Type getType() {
        return Type.DAY_OF_WEEK_WEEK_OF_MONTH;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        DayOfWeek dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(5));
        WeekOfMonth weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth(m.group(3));
        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setWeekOfMonth(weekOfMonth);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
