package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;

public class TimeIntervalRule14 extends ExtractionRule

{
    private String rule = "\\b(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[,]?[\\s]*([01]?[0-9]|2[0-3])(\\s)?(\\.|,|:)([0-5][0-9])(\\s|')(ile|le|la)\\s([01]?[0-9]|2[0-3])(\\s)?(\\.|,|:)([0-5][0-9])\\s(arası)\\b";
    private String ruleEn = "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[,]?[\\s]*\\b([01]?[0-9]|2[0-3])[-]([01]?[0-9]|2[0-3])\\b";
    
    protected String example = "Pazartesi 11.00'le 16.00 arası, Salı 12:00 ile 13:00 arasında";
    protected String exampleEn = "Monday, 11-16";
    protected Locale locale = Locale.US;
    protected double confidence = 0.5;
    private int priority = 5;
    protected UUID id = UUID.fromString("f4ffb605-f71d-4860-b9cd-8cde74cb603b");

    public TimeIntervalRule14() {
    }

    @Override
    public Type getType() {
        return Type.DATE_TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();
        Date datefrom = new Date();
        Date dateTo = new Date();

        if (m.group(2) != null) {
            DayOfWeek dayOfWeek = null;
            dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(2));

            if (dayOfWeek != null) {
                datefrom.setDayOfWeek(dayOfWeek);
                dateTo.setDayOfWeek(dayOfWeek);

            }
        }

        if (m.group(4) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(4)));

        }
        if (m.group(7) != null) {
            timeFrom.setMinutes(Integer.parseInt(m.group(7)));
        }
        if (m.group(10) != null) {
            timeTo.setHours(Integer.parseInt(m.group(10)));
        }

        if (m.group(13) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(13)));
        }


        start.setDate(datefrom);
        start.setTime(timeFrom);
        end.setDate(dateTo);
        end.setTime(timeTo);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
        List<Temporal> result = new ArrayList<Temporal>();
        result.add(temporal);
        return result;
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
