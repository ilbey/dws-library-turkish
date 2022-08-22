package com.bezkoder.springjwt.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

public class MonthDayYearRule2 extends ExtractionRule {
    private double confidence = 0.6;
    private int priority = 2;
    protected String rule = "\\b(([1-2][0-9])|([3][0-1])|([0][1-9]))(((,)|(-)|(.)|(\\/))?[\\s]*)(([0]?|[12])[0-9])(((,)|(-)|(.)|(\\/))?[\\s]*)((19|20)\\d{2})\\b";
    protected String ruleEn = "\\b((0?[1-9]|[12][0-9]|3[01])[.\\/](0?[1-9]|1[012])[.\\/](([12][0-9])?\\d\\d))\\b";
    protected String example = "30/10/2012, 22.11.2014";
    protected UUID id = UUID.fromString("e3d69ad4-b45b-45f5-bb3d-b49c5739802d");

    public MonthDayYearRule2() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        String dayString = m.group(1);
        String monthString = m.group(11);
        String yearString = m.group(19);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = 0;
        if (yearString.length() == 4) {
            year = Integer.parseInt(yearString);
        }
        if (yearString.length() == 2) {
            year = Integer.parseInt("20" + yearString);
        }
        Date date = new Date();
        date.setDay(day);
        date.setMonth(month);
        date.setYear(year);
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
