package com.bezkoder.springjwt.rules.repeated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;

public class Every extends ExtractionRule {

    private double confidence = 0.2;
    private String ruleEn = "\\b(every|each)\\b";
    private String rule = "\\b(her|her bir)\\b";
    private int priority = 2;
    protected String example = "every/each (rule is used only for composite rules.txt, not as a simple rule )";
    protected UUID id = UUID.fromString("a69ee2a4-64fe-431a-9931-ddfa7df9a43d");

    public Every() {
    }

    @Override
    public Type getType() {
        return Type.EVERY;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Temporal temporal = new Temporal();
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
