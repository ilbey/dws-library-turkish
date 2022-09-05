package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.utils.Utils;

public class TimeIntervalRule13 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b(saat\\s)?([01]?[0-9]|2[0-3])(\\s)?(\\.|,|:)([0-5][0-9])('|\\s*)(den|dan|ten|tan)(\\s)(saat\\s)?([01]?[0-9]|2[0-3])(\\s)?(\\.|,|:)([0-5][0-9])('|\\s*)(e|a|ye|ya)((\\s)kadar)?\\b";
    private String ruleEn = "\\b(([01]?[0-9]|2[0-3])[:]([0-5][0-9])[\\s]*[-|�|to][\\s]*([01]?[0-9]|2[0-3])[:]([0-5][0-9]))\\b";
   
    protected String example = "20:22'den 23:30'a, saat 20.22'den 23.30'a kadar";
    protected String exampleEn = "20:22 to 23:30";
    protected UUID id = UUID.fromString("c444e037-eb63-4e73-a6e2-c9db29f1b8c9");

    public TimeIntervalRule13() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;
        if (m.group(2) != null) {
            int hours = Integer.parseInt(m.group(2));
            timeFrom.setHours(hours);

        }
        if (m.group(5) != null) {
            timeFrom.setMinutes(Integer.parseInt(m.group(5)));
        }
        if (m.group(10) != null) {
            int hours = Integer.parseInt(m.group(10));
            timeTo.setHours(hours);
        }

        if (m.group(13) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(13)));
        }

        start.setTime(timeFrom);
        end.setTime(timeTo);

        temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;

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

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
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

}
