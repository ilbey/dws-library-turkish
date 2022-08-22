package com.bezkoder.springjwt.rules.timezone;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

public class TimeZone extends ExtractionRule {
    private TemporalBasicCaseParser parser = new TemporalBasicCaseParser();
    protected Locale locale = Locale.US;
    protected double confidence = 0.3;
    private int priority = 1;
    private String rule = "\\b" + TemporalConstants.TIME_ZONE + "\\b";
    protected String example = "CET, UTC, etc. (rule is used only for composite rules.txt, not as a simple rule )";
    protected UUID id = UUID.fromString("4803fc1e-9c43-4e78-b5af-51865c5c3ed1");

    public TimeZone() {
    }

    @Override
    public Type getType() {
        return Type.TIMEZONE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Time time = new Time();
        int hours = 0;
        int timezone = 0;
        if (m.group() != null) {
            timezone = parser.getTimeZone(m.group());
            time.setTimezone(timezone);
        }
        time.setHours(hours);
        time.setTimezoneName(m.group());
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(type, time);
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

    public UUID getId() {
        return id;
    }
}
