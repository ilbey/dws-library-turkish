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

// 6/27

public class MonthAndDayRule5 extends ExtractionRule {
    private double confidence = 0.2;
    private int priority = 1;
    
    //private String rule = "\\b(([1-2][0-9])|([3][0-1])|([0][1-9]))(((,)|(-)|(.)|(\\/)|([\\s]*))[\\s]*)(([0]?|[12])[0-9])\\b";
    private String rule = "\\b(([1-2][0-9])|([3][0-1])|([0][1-9]))((,)|(-)|(.)|(\\/))(([0]?|[12])[0-9])\\b";
    private String ruleEn = "\\b(([0]?([1-9])|([1-2][0-9])|([3][0-1]))\\/(([0]?[1-9])|[1][0-2]))\\b";
    protected String example = "27/6";
    protected String exampleEn = "6/27";
    protected UUID id = UUID.fromString("79607c59-fae0-424b-a8a6-7adb0f29194b");

    public MonthAndDayRule5() {
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Date date = new Date();

        if (m.group(10) != null) {
            int month = Integer.parseInt(m.group(10));
            date.setMonth(month);

        }
        int day = Integer.parseInt(m.group(1));

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
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
