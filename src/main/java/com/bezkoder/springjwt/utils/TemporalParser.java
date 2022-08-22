package com.bezkoder.springjwt.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.rules.date.relative.NTimeAgoRule;
import com.bezkoder.springjwt.rules.date.relative.NTimeAgoRule2;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.Duration;
import com.bezkoder.springjwt.temporal.entities.DurationInterval;
import com.bezkoder.springjwt.temporal.entities.Frequency;
import com.bezkoder.springjwt.temporal.entities.Holidays;
import com.bezkoder.springjwt.temporal.entities.Set;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.temporal.entities.WeekOfMonth;

/**
 * <h1>Temporal Parser Utils Class</h1> is used for getting Temporal objects by
 * specified parameters(season, holidays, durations, etc.)
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */

public class TemporalParser {

    public Temporal getSeason(String season, int year) {
        if (year == 0) {
            year = new LocalDate().getYear();
        }
        if (season == null || season.isEmpty()) {
            return null;
        }
        if (season.equalsIgnoreCase("Yaz")) {
            return buildTemporal("01-06-" + year, "31-08-" + year);
        }
        if (season.equalsIgnoreCase("Kış")) {
            // as end date will be in new year
            int next_year = year + 1;
            boolean leap = isLeapYear(year);
            if (leap) {
                return buildTemporal("01-12-" + year, "29-02-" + next_year);
            }
            else{
                return buildTemporal("1-12-" + year, "28-02-" + next_year);
            }
        }
        if (season.equalsIgnoreCase("Sonbahar")) {
            return buildTemporal("01-09-" + year, "30-11-" + year);
        }
        if (season.equalsIgnoreCase("İlkbahar") || season.equalsIgnoreCase("Bahar")) {
            return buildTemporal("01-03-" + year, "31-05-" + year);
        }
        return null;
    }

    public boolean isLeapYear(int year) {
        if (year == 0) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    public Temporal getHoliday(String holidayName) {

        if (holidayName == null || holidayName.isEmpty()) {
            return null;
        }
        Temporal temporal = null;
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        if (holidayName.equalsIgnoreCase(Holidays.NEW_YEAR)
                || holidayName.equalsIgnoreCase(Holidays.NEW_YEAR4)) {
            return buildTemporal("01-01-00", "01-01-00");

        } else if (holidayName.equalsIgnoreCase(Holidays.HALLOWEEN)) {
            return buildTemporal("31-10-00", "31-10-00");
        }

        else if (holidayName.contains("Sevgililer Günü")) {
            return buildTemporal("14-02-00", "14-02-00");
        }

        else if (holidayName.equalsIgnoreCase(Holidays.CHRISTMAS) || holidayName.equalsIgnoreCase(Holidays.CHRISTMAS2) || holidayName.equalsIgnoreCase(Holidays.CHRISTMAS3)) {
            return buildTemporal("25-12-00", "25-12-00");
        }

        else if (holidayName.equalsIgnoreCase(Holidays.THANKSGIVING2)) {
            Date startDate = new Date(0, 11, 0);
            Date endDate = new Date(0, 11, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            endDate.setWeekOfMonth(WeekOfMonth.FOURTH);
            startDate.setDayOfWeek(DayOfWeek.TH);
            endDate.setDayOfWeek(DayOfWeek.TH);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
            temporal.setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.INDEPENDENCE_DAY)) {
            return buildTemporal("04-07-00", "04-07-00");
        }

        else if (holidayName.equalsIgnoreCase(Holidays.ST_VALENTINE)){
            return buildTemporal("14-02-00", "14-02-00");
        }

        else if (holidayName.equalsIgnoreCase(Holidays.INAUGURATION_DAY)) {
            return buildTemporal("20-01-00", "20-01-00");
        }

        else if (holidayName.equalsIgnoreCase(Holidays.MEMORIAL)) {
            Date startDate = new Date(0, 5, 0);
            startDate.setWeekOfMonth(WeekOfMonth.LAST);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 5, 0);
            endDate.setWeekOfMonth(WeekOfMonth.LAST);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.LABOR)) {
            Date startDate = new Date(0, 9, 0);
            startDate.setWeekOfMonth(WeekOfMonth.FIRST);
            startDate.setDayOfWeek(DayOfWeek.MO);
            Date endDate = new Date(0, 9, 0);
            endDate.setWeekOfMonth(WeekOfMonth.FIRST);
            endDate.setDayOfWeek(DayOfWeek.MO);
            start.setDate(startDate);
            end.setDate(endDate);
            temporal = new Temporal(start, end);
            temporal.setType(Type.DAY_OF_WEEK_WEEK_OF_MONTH);

        }

        else if (holidayName.equalsIgnoreCase(Holidays.VETERANS_DAY)) {
            return buildTemporal("11-11-00", "11-11-00");
        }

        if (temporal != null && temporal.getType() == null) {
            temporal.setType(Type.DATE);
        }
        return temporal;
    }

    public Temporal getRelativeTemporalObjectByProperty(String property, LocalDateTime localDate) {
        if (localDate == null) {
            localDate = new LocalDateTime();
        }
        if (property.equalsIgnoreCase("bugün")) {
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            timeDate.setTime(time);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }
        if (property.equalsIgnoreCase("yarın")) {
            localDate = localDate.plusDays(1);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            timeDate.setTime(time);

            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("bu gece")) {
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(19, 0, 0);
            time.setTimezone(0);

            timeDate.setTime(time);

            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }

        if (property.equalsIgnoreCase("dün")) {
            localDate = localDate.minusDays(1);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);

        }

        if (property.equalsIgnoreCase("önceki gün")) {
            localDate = localDate.minusDays(2);
            TimeDate timeDate = Utils.getTimeDateUTC(localDate);
            Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
            time.setTimezone(0);
            return TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        }
        return null;
    }

    /* get by dayOfWeek */

    public Temporal getRelativeTemporalObjectByDayOfWeek(DayOfWeek dayOfWeek, LocalDateTime localDate) {
        int currentDayOfWeek = localDate.getDayOfWeek();
        int parsedDayOfWeek = dayOfWeek.getValue();
        if (parsedDayOfWeek >= currentDayOfWeek) {
            localDate = localDate.plusDays(parsedDayOfWeek - currentDayOfWeek);
        } else if (currentDayOfWeek > parsedDayOfWeek) {
            localDate = localDate.plusDays(7 + parsedDayOfWeek - currentDayOfWeek);
        }
        Time time = new Time(localDate.getHourOfDay(), localDate.getMinuteOfHour(), 0);
        TimeDate timeDate = Utils.getTimeDate(localDate);
        time.setTimezone(0);
        timeDate.setTime(time);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        temporal.getStartDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getEndDate().getDate().setDayOfWeek(dayOfWeek);
        return temporal;
    }

    /* get by dayOfWeek and weekOfMonth */

    public Temporal getRelativeTemporalObjectByWeekOfMonth(DayOfWeek dayOfWeek, WeekOfMonth weekOfMonth, LocalDateTime localDate) {
        int currentDayOfWeek = localDate.getDayOfWeek();
        int currentWeek = currentWeekOfMonth(localDate);

        int parsedDayOfWeek = dayOfWeek.getValue();
        int parsedWeekOfMonth = weekOfMonth.getValue();

        if (weekOfMonth == WeekOfMonth.LAST) {
            int day = getLastWeekdayOfMonth(parsedDayOfWeek, localDate.getMonthOfYear(), localDate.getYear());
            if (day > localDate.getDayOfMonth()) {
                localDate = localDate.withDayOfMonth(day);
            } else {
                day = getLastWeekdayOfMonth(parsedDayOfWeek, localDate.plusMonths(1).getMonthOfYear(), localDate.getYear());
                localDate = localDate.withDayOfMonth(day);
            }
        } else if (weekOfMonth == WeekOfMonth.FIRST) {
            LocalDateTime newLocalDate = nthWeekdayOfMonth(parsedDayOfWeek, localDate.getMonthOfYear(), localDate.getYear(), 1);
            if (newLocalDate.getDayOfMonth() < localDate.getDayOfMonth()) {
                localDate = nthWeekdayOfMonth(parsedDayOfWeek, localDate.plusMonths(1).getMonthOfYear(), localDate.getYear(), 1);
            } else {
                localDate = newLocalDate;
            }

        } else if (currentWeek > parsedWeekOfMonth) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.plusMonths(1).getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
        } else if (currentWeek < parsedWeekOfMonth) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
        } else if (currentWeek == parsedWeekOfMonth) {
            if (parsedDayOfWeek < currentDayOfWeek) {
                localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.plusMonths(1).getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
            } else {
                localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
            }
        } else if (currentWeek == weekOfMonth.getValue() && currentDayOfWeek < parsedDayOfWeek) {
            localDate = nthWeekdayOfMonth(dayOfWeek.getValue(), localDate.getMonthOfYear(), localDate.getYear(), weekOfMonth.getValue());
        }

        TimeDate timeDate = Utils.getTimeDate(localDate);

        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        temporal.getStartDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getStartDate().getDate().setWeekOfMonth(weekOfMonth);
        temporal.getEndDate().getDate().setDayOfWeek(dayOfWeek);
        temporal.getEndDate().getDate().setWeekOfMonth(weekOfMonth);
        return temporal;

    }

    private LocalDateTime nthWeekdayOfMonth(int dayOfWeek, int month, int year, int n) {
        LocalDateTime start = new LocalDateTime(year, month, 1, 0, 0);
        LocalDateTime date = start.withDayOfWeek(dayOfWeek);
        return (date.isBefore(start)) ? date.plusWeeks(n) : date.plusWeeks(n - 1);
    }

    private int currentWeekOfMonth(LocalDateTime localDate) {
        int date = localDate.getDayOfMonth();
        int weekOfMonth = date % 7;

        return weekOfMonth;
    }

    private int getLastWeekdayOfMonth(int dayweek, int month, int year) {
        LocalDateTime d = new LocalDateTime(year, month, 1, 0, 0).plusMonths(1).withDayOfWeek(dayweek);
        if (d.getMonthOfYear() != month)
            d = d.minusWeeks(1);
        return d.getDayOfMonth();
    }

    public Temporal getDurationInterval(Temporal duration1, Temporal duration2) {
        Temporal temporal = new Temporal();
        temporal.setType(Type.DURATION_INTERVAL);
        DurationInterval interval = new DurationInterval(duration1.getDuration(), duration2.getDuration());
        temporal.setDurationInterval(interval);
        return temporal;
    }

    public Temporal getDuration(String durationType, int durationLength) {
        Duration duration = null;
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("dakika") || durationType.equalsIgnoreCase("dak") || durationType.equalsIgnoreCase("dk")) {
            duration = new Duration();
            duration.setMinutes(durationLength);
        }

        if (durationType.equalsIgnoreCase("saat") || durationType.equalsIgnoreCase("sa")) {
            duration = new Duration();
            duration.setHours(durationLength);
        }

        if (durationType.equalsIgnoreCase("hafta")) {
            duration = new Duration();
            duration.setWeeks(durationLength);
        }

        if (durationType.equalsIgnoreCase("ay")) {
            duration = new Duration();
            duration.setMonths(durationLength);
        }

        if (durationType.equalsIgnoreCase("yıl")) {
            duration = new Duration();
            duration.setYears(durationLength);
        }

        if (durationType.equalsIgnoreCase("gün") || durationType.equalsIgnoreCase("gece")) {
            duration = new Duration();
            duration.setDays(durationLength);
        }

        if (durationType.equalsIgnoreCase("saniye") || durationType.equalsIgnoreCase("san") || durationType.equalsIgnoreCase("sn")) {
            duration = new Duration();
            duration.setSeconds(durationLength);
        }

        return TemporalObjectGenerator.generateTemporalDuration(Type.DURATION, duration);

    }

    public Temporal getRelativeDurationDate(String text, LocalDateTime dateTime) {
        Matcher m = Utils.getMatch(new NTimeAgoRule().getRule(), text);
        int durationLength = Integer.parseInt(m.group(1));
        String durationType = m.group(3);
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("dakika") || durationType.equalsIgnoreCase("dak") || durationType.equalsIgnoreCase("dk")) {
            dateTime = dateTime.minusMinutes(durationLength);

        }

        if (durationType.equalsIgnoreCase("saat") || durationType.equalsIgnoreCase("sa")) {
            dateTime = dateTime.minusHours(durationLength);

        }

        if (durationType.equalsIgnoreCase("hafta")) {
            dateTime = dateTime.minusWeeks(durationLength);

        }

        if (durationType.equalsIgnoreCase("ay")) {
            dateTime = dateTime.minusMonths(durationLength);

        }

        if (durationType.equalsIgnoreCase("yıl")) {
            dateTime = dateTime.minusYears(durationLength);

        }

        if (durationType.equalsIgnoreCase("gün")) {
            dateTime = dateTime.minusDays(durationLength);

        }

        if (durationType.equalsIgnoreCase("saniye") || durationType.equalsIgnoreCase("san") || durationType.equalsIgnoreCase("sn")) {
            dateTime = dateTime.minusSeconds(durationLength);

        }
        TimeDate timeDate = Utils.getTimeDate(dateTime);
        timeDate.getTime().setTimezone(0);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        return temporal;

    }

    public Temporal getRelativeDurationDate2(String text, LocalDateTime dateTime) {
        Matcher m = Utils.getMatch(new NTimeAgoRule2().getRule(), text);
        int durationLength = TemporalBasicCaseParser.getIntFromBasicTerm(m.group(2));
        String durationType = m.group(8);
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (durationType == null) {
            return null;
        }
        if (durationType.equalsIgnoreCase("dakika") || durationType.equalsIgnoreCase("dak") || durationType.equalsIgnoreCase("dk")) {

            dateTime = dateTime.minusMinutes(durationLength);

        }

        if (durationType.equalsIgnoreCase("saat") || durationType.equalsIgnoreCase("sa")) {
            dateTime = dateTime.minusHours(durationLength);

        }

        if (durationType.equalsIgnoreCase("hafta")) {
            dateTime = dateTime.minusWeeks(durationLength);

        }

        if (durationType.equalsIgnoreCase("ay")) {
            dateTime = dateTime.minusMonths(durationLength);

        }

        if (durationType.equalsIgnoreCase("yıl")) {
            dateTime = dateTime.minusYears(durationLength);

        }

        if (durationType.equalsIgnoreCase("gün")) {
            dateTime = dateTime.minusDays(durationLength);

        }

        if (durationType.equalsIgnoreCase("saniye") || durationType.equalsIgnoreCase("san") || durationType.equalsIgnoreCase("sa")) {
            dateTime = dateTime.minusSeconds(durationLength);

        }
        TimeDate timeDate = Utils.getTimeDate(dateTime);
        timeDate.getTime().setTimezone(0);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(Type.DATE, timeDate);
        return temporal;

    }

    public Temporal getTimeOfDay(String timeOfDay) {
        Temporal temporal = null;
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();
        Type type = Type.TIME_INTERVAL;

        if (timeOfDay.equalsIgnoreCase("sabah") || timeOfDay.equalsIgnoreCase("sabahlar")) {
            Time startTime = new Time(5, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("öğleden sonra") || timeOfDay.equalsIgnoreCase("öğleden sonraları")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(18, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);
        }
        if (timeOfDay.equalsIgnoreCase("akşam") || timeOfDay.equalsIgnoreCase("akşamlar")) {
            Time startTime = new Time(18, 0, 0);
            Time endTime = new Time(22, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end);
        }
        if (timeOfDay.equalsIgnoreCase("gece") || timeOfDay.equalsIgnoreCase("geceler")) {
            Time startTime = new Time(22, 0, 0);
            Time endTime = new Time(23, 59, 59);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("gece yarısı") || timeOfDay.equalsIgnoreCase("gece yarıları")) {
            Time startTime = new Time(0, 0, 0);
            Time endTime = new Time(0, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("öğle") || timeOfDay.equalsIgnoreCase("öğleler")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);

        }
        if (timeOfDay.equalsIgnoreCase("öğlen") || timeOfDay.equalsIgnoreCase("öğlenler")) {
            Time startTime = new Time(12, 0, 0);
            Time endTime = new Time(12, 0, 0);
            start.setTime(startTime);
            end.setTime(endTime);
            temporal = new Temporal(start, end, type);
        }
        return temporal;

    }

    public Temporal getTemporalForEveryPeriod(String period) {
        Temporal temporal = new Temporal();
        Set set = new Set();
        temporal.setType(Type.SET);

        if (period.equalsIgnoreCase("gün") || period.equalsIgnoreCase("günlük") || period.equalsIgnoreCase("gündelik")) {
            set.setFrequency(Frequency.DAILY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("hafta") || period.equalsIgnoreCase("haftalık")) {
            set.setFrequency(Frequency.WEEKLY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("ay") || period.equalsIgnoreCase("aylık")) {
            set.setFrequency(Frequency.MONTHLY);
            temporal.setSet(set);
            return temporal;

        } else if (period.equalsIgnoreCase("yıl") || period.equalsIgnoreCase("sene") || period.equalsIgnoreCase("yıllık") || period.equalsIgnoreCase("senelik")) {
            set.setFrequency(Frequency.YEARLY);
            temporal.setSet(set);
            return temporal;
        }

        else if (period.equalsIgnoreCase("haftasonu")) {
            set.setFrequency(Frequency.WEEKLY);
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
            daysOfWeek.add(DayOfWeek.SA);
            daysOfWeek.add(DayOfWeek.SU);
            set.setByDay(daysOfWeek);
            temporal.setSet(set);
            return temporal;
        }

        else if (period.equalsIgnoreCase("saatlik")) {
            set.setFrequency(Frequency.HOURLY);
            temporal.setSet(set);
            return temporal;

        }

        else if (period.equalsIgnoreCase("haftaiçi")) {
            set.setFrequency(Frequency.WEEKLY);
            ArrayList<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
            daysOfWeek.add(DayOfWeek.MO);
            daysOfWeek.add(DayOfWeek.TU);
            daysOfWeek.add(DayOfWeek.WE);
            daysOfWeek.add(DayOfWeek.TH);
            daysOfWeek.add(DayOfWeek.FR);
            set.setByDay(daysOfWeek);
            temporal.setSet(set);
            return temporal;
        } else {
            return null;
        }

    }

    private Temporal buildTemporal(String start, String end) {
        String startEl[] = start.split("-");
        String endEl[] = end.split("-");
        TimeDate start_date = new TimeDate();
        TimeDate end_date = new TimeDate();
        Date startDate = new Date(Integer.parseInt(startEl[2]), Integer.parseInt(startEl[1]), Integer.parseInt(startEl[0]));
        Date endDate = new Date(Integer.parseInt(endEl[2]), Integer.parseInt(endEl[1]), Integer.parseInt(endEl[0]));
        start_date.setDate(startDate);
        end_date.setDate(endDate);
        return new Temporal(start_date, end_date);
    }

}
