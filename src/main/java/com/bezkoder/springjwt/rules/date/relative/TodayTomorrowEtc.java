package com.bezkoder.springjwt.rules.date.relative;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;

public class TodayTomorrowEtc extends ExtractionRule {
    private double confidence = 0.9;
    private int priority = 2;
    protected String ruleEn = "\\b((today)|(yesterday)|(tomorrow)|(the day before yesterday)|(tonight))\\b";
    protected String rule = "\\b((bugün)|(dün)|(yarın)|(önceki gün)|(ertesi gün)|(bu gece))\\b";
    protected String example = "today, tomorrow, yesterday, the day before yesterday, tonight";
    protected UUID id = UUID.fromString("315f23c5-90da-4b2c-8d32-c9249d18aa75");

    public TodayTomorrowEtc() {
    }

    @Override
    public Type getType() {
        return Type.RELATIVE_TODAY;
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
