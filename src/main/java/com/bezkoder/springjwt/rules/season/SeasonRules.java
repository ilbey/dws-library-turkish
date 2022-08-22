package com.bezkoder.springjwt.rules.season;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.constants.TemporalConstants;

public class SeasonRules extends ExtractionRule {

    private TemporalParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.4;
    private String ruleEn = "\\b(((in)[\\s]*|(in the|throughout)[\\s]([\\s]the)?)[\\s]*)?" + TemporalConstants.SEASON + "[s]?([\\s]*(month|months|hours))?\\b";
    private String rule = "\\b" + TemporalConstants.SEASON + "([\\s]*(ayı|ayları|saati|saatleri))?\\b";
    protected int priority = 1;
    protected String example = "summer, fall, summer hours, winter months, etc.";
    protected UUID id = UUID.fromString("8b28436d-b929-4db8-81bd-d564419f8ce8");

    public SeasonRules() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DATE_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        Temporal temporal = parser.getSeason(m.group(6), 0);
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
