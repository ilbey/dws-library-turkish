package com.bezkoder.springjwt.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.MonthOfYear;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// March the Fourteenth, 2011

public class MonthAndDayOrderRule1 extends ExtractionRule {
    private double confidence = 0.99;
    private int priority = 3;
    private String rule = "\\b(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[\\s]*(the)?[\\s]*(" + TemporalConstants.BASIC_ORDER
            + ")[,]?([\\s]*of)?[\\s]*([12]\\d\\d\\d)?\\b";
    protected String example = " March the Fourteenth, 2011";
    protected UUID id = UUID.fromString("887414c3-55a8-438f-a4b7-2f7a85f2398e");

    public MonthAndDayOrderRule1() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Date date = new Date();

        if (m.group(8) != null) {
            date.setYear(Integer.parseInt(m.group(8)));
        }
        if (m.group(1) != null) {
            MonthOfYear currentMonth = TemporalBasicCaseParser.getMonthOfYear(m.group(1));
            date.setMonth(currentMonth.getValue());

        }

        int day = TemporalBasicCaseParser.getDayOfWeekFromOrder(m.group(5));
        date.setDay(day);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
        return result;
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