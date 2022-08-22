package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// 12h00-18h00

public class TimeIntervalRule11 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b(([01]?[0-9]|2[0-3])h([0-5][0-9])[\\s]*[-|to][\\s]*([01]?[0-9]|2[0-3])h([0-5][0-9]))\\b";
    protected String example = "12h00-18h00";
    protected UUID id = UUID.fromString("1ff95fcd-aa0a-40a1-8779-9876d84760d7");

    public TimeIntervalRule11() {

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
            timeFrom.setHours(Integer.parseInt(m.group(2)));
        }

        if (m.group(3) != null) {
            timeFrom.setMinutes(Integer.parseInt(m.group(3)));
        }

        if (m.group(4) != null) {
            timeTo.setHours(Integer.parseInt(m.group(4)));
        }

        if (m.group(5) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(5)));
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
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public UUID getId() {
        return id;
    }
}
