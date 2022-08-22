package com.bezkoder.springjwt.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.temporal.entities.MonthOfYear;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// the second of December
public class DayOrderAndMonthRule1 extends ExtractionRule {

    protected double confidence = 0.99;
    private int priority = 2;
    private String rule = "\\b(" + TemporalConstants.MONTH_OF_YEAR + ")(((')?(ın|in|un|ün))|([\\s]*)(ayının))([\\s]*)(\\d)+(([\\s]*)|(')|(.)([\\s]*)?)?(si|günü)\\b";

    private String ruleEn = "\\b((the)?[\\s]*)" + TemporalConstants.BASIC_ORDER + "([\\s]*(of)?[\\s]*(the)?[\\s]*)?(" + TemporalConstants.MONTH_OF_YEAR + "|" + TemporalConstants.MONTH_OF_YEAR_EASY
    + ")[,;\\s]?(([12][0-9]\\d\\d))?\\b";


    protected String example = "Aralık'ın 2si, Aralık ayının 2. günü, Aralığın 2'si, Aralık ayının 2'si";
    protected String exampleEn = "the second of December";
    protected UUID id = UUID.fromString("fae4f73e-6257-40aa-a66c-93773d175675");

    public DayOrderAndMonthRule1() {

    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        int dayOfMonth = 0;
        MonthOfYear monthOfYear = null;

        monthOfYear = TemporalBasicCaseParser.getMonthOfYear((m.group(1)));
        dayOfMonth = TemporalBasicCaseParser.getStringtoInt((m.group(10)));
        Date date = new Date();

        date.setDay(dayOfMonth);

        if (monthOfYear != null) {
            date.setMonth(monthOfYear.getValue());
        }
        
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
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
