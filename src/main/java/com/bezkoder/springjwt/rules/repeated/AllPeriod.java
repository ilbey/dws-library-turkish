package com.bezkoder.springjwt.rules.repeated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;

public class AllPeriod extends ExtractionRule {
    private TemporalParser parser;
    private double confidence = 0.9;
    private String rule = "\\b((bütün|tüm)[\\s]*(gün|hafta|ay|yıl))\\b";
    protected String example = "whole year, all day, full month, entire week";
    protected UUID id = UUID.fromString("8b48600c-fcaf-4379-a604-6c74678522a7");

    private int priority = 2;
    {
        parser = new TemporalParser();
    }

    public AllPeriod() {
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getDuration(m.group(3), 1);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
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