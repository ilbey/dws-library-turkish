package com.bezkoder.springjwt.rules.date.relative;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;

public class NTimeAgoRule2 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.7;
    private int priority = 5;
    protected String ruleEn = "\\b" + "((" + TemporalConstants.BASIC_NUMBER_ONE_TEN + "|" + "([\\s]*" + TemporalConstants.BASIC_NUMBER_TWENTY_HUNDRED + ")" + "|"
            + "([\\s]*" + TemporalConstants.BASIC_NUMBER_ELEVEN_NINETEEN + "))[\\s]*((" + TemporalConstants.DURATION + "))[\\s]*(ago))\\b";
    protected String rule = "\\b" + "((" + TemporalConstants.BASIC_NUMBER_ONE_TEN + "|" + "([\\s]*" + TemporalConstants.BASIC_NUMBER_TWENTY_HUNDRED + ")" + "|"
            + "([\\s]*" + TemporalConstants.BASIC_NUMBER_ELEVEN_NINETEEN + "))[\\s]*((" + TemporalConstants.DURATION + "))[\\s]*(önce))";
    protected String example = "one hour ago, two weeks ago, month ago";
    protected UUID id = UUID.fromString("74b57dd8-ccd7-40c5-b1b5-cff2ad657b88");

    public NTimeAgoRule2() {
    }

    @Override
    public Type getType() {
        return Type.RELATIVE_DATE_ORDER;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        List<Temporal> results = new ArrayList<Temporal>();
        Temporal temporal = new Temporal();
        results.add(temporal);
        return results;
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
