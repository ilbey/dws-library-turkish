package com.bezkoder.springjwt.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;

public class DurationRule1 extends ExtractionRule {
    private TemporalParser parser;
    private double confidence = 0.8;
    private int priority = 4;
    private String rule = "\\b((lasts|about|past|at least|up to|more than|less than|last|after)[\\s]*)?(([1-9])|([1-9][0-9])|([1-9][0-9][0-9]))" + "([\\s]*[-]?" + TemporalConstants.DURATION + "\\b)";
    protected String example = "lasts n days, more than 3 hours, etc.";
    protected UUID id = UUID.fromString("a8578915-03f3-4297-b9cd-6486974e1feb");

    public DurationRule1() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        int duration = 0;
        if (m.group(3) != null) {
            duration = Integer.parseInt(m.group(3));
        }
        Temporal temporal = parser.getDuration(m.group(8), duration);
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

}
