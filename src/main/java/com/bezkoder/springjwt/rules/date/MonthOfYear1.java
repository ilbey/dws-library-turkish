package com.bezkoder.springjwt.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.Utils;

// November, December, etc.

public class MonthOfYear1 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.3;
    private int priority = 2;
    protected String rule = "\\b((through[\\s]*|thru[\\s]*|in[\\s]*)?(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY + ")[.]?)\\b";
    protected String example = "in December, through Febryary, January, February, etc.";
    protected UUID id = UUID.fromString("bb70b339-738c-4c32-88ff-6ede9803e14b");

    public MonthOfYear1() {
    }

    @Override
    public Type getType() {
        return Type.MONTH;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int month = TemporalBasicCaseParser.getMonthOfYear(m.group(3)).getValue();
        Date date = new Date();
        date.setMonth(month);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

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

    public void setId(UUID id) {
        this.id = id;
    }
}
