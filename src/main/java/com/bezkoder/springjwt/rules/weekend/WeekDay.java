package com.bezkoder.springjwt.rules.weekend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;

public class WeekDay extends ExtractionRule {

    private double confidence = 0.9;
    private String rule = "\\b(week days|week day|week days|week day|weekday|weekdays|week-days|week-day)\\b";
    protected int priority = 1;
    protected String example = "week days, weekday, etc. ";
    protected UUID id = UUID.fromString("04562d23-7c1d-4bb6-b1e6-95b237c1490e");

    public WeekDay() {
    }

    @Override
    public Type getType() {
        return Type.DAY_OF_WEEK_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setDayOfWeek(DayOfWeek.MO);
        endDate.setDayOfWeek(DayOfWeek.FR);

        start.setDate(startDate);
        end.setDate(endDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE_INTERVAL, start, end);
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
