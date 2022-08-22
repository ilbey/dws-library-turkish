package com.bezkoder.springjwt.rules.date.dayofweek;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.WeekOfMonth;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;

//1st Tuesday of the month
public class DayOfWeekOrderRule2 extends ExtractionRule {

    protected double confidence = 0.9;
    private String ruleEn = "\\b(the[\\s]*)?(([1-5])(th|st|nd|rd)?[\\s]*)" + "((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY
            + "))([s]?[\\s]*(of[\\s]*)(the[\\s]*)?(month))?\\b";

    private String rule = "\\b(ayın[\\s]*)?(([1-5])((inci)|(ıncı)|(üncü)|(uncu))?[\\s]*)" + "((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY
            + "((sı)|(si|(ı)?))?))\\b";
    private int priority = 3;
    protected String example = "1st Tuesday of the month";
    protected UUID id = UUID.fromString("80bd5c57-71ee-4bde-ac7c-529481987d70");

    public DayOfWeekOrderRule2() {

    }

    public Type getType() {
        return Type.DAY_OF_WEEK_WEEK_OF_MONTH;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        DayOfWeek dayOfWeek = null;
        WeekOfMonth weekOfMonth = null;
        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(5));
        weekOfMonth = TemporalBasicCaseParser.getWeekOfMonth(m.group(3));
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

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
