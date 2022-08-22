package com.bezkoder.springjwt.utils;

import com.bezkoder.springjwt.temporal.entities.WeekOfMonth;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.MonthOfYear;
import com.bezkoder.springjwt.temporal.entities.TimeTag;

public class TemporalBasicCaseParser {

    public static DayOfWeek getDayOfWeek(String text) {

        if (text.equalsIgnoreCase("Pazartesi") || text.equalsIgnoreCase("Pte") || text.equalsIgnoreCase("Pazartesiler") || text.equalsIgnoreCase("Pazartesisi")) {
            return DayOfWeek.MO;
        }
        if (text.equalsIgnoreCase("Salı") || text.equalsIgnoreCase("Sal") || text.equalsIgnoreCase("Salılar") || text.equalsIgnoreCase("Salısı")) {
            return DayOfWeek.TU;
        }
        if (text.equalsIgnoreCase("Çarşamba") || text.equalsIgnoreCase("Çar") || text.equalsIgnoreCase("Çarşambalar") || text.equalsIgnoreCase("Çarşambası")) {
            return DayOfWeek.WE;
        }
        if (text.equalsIgnoreCase("Perşembe") || text.equalsIgnoreCase("Per") || text.equalsIgnoreCase("Perşembeler") || text.equalsIgnoreCase("Perşembesi")) {
            return DayOfWeek.TH;
        }
        if (text.equalsIgnoreCase("Cuma") || text.equalsIgnoreCase("Cum") || text.equalsIgnoreCase("Cumalar") || text.equalsIgnoreCase("Cuması")) {
            return DayOfWeek.FR;
        }
        if (text.equalsIgnoreCase("Cumartesi") || text.equalsIgnoreCase("Cte") || text.equalsIgnoreCase("Cumartesiler") || text.equalsIgnoreCase("Cumartesisi")) {
            return DayOfWeek.SA;
        }
        if (text.equalsIgnoreCase("Pazar") || text.equalsIgnoreCase("Paz") || text.equalsIgnoreCase("Pazarlar") || text.equalsIgnoreCase("Pazarı")) {
            return DayOfWeek.SU;
        }
        return null;
    }

    public static MonthOfYear getMonthOfYear(String text) {
        if (text == null) {
            return null;
        }
        if (text.equalsIgnoreCase("Ocak") || text.equalsIgnoreCase("Oca") || text.equalsIgnoreCase("Ocağ")) {
            return MonthOfYear.JANUARY;
        }
        if (text.equalsIgnoreCase("Şubat") || text.equalsIgnoreCase("Şub")) {
            return MonthOfYear.FEBRUARY;
        }
        if (text.equalsIgnoreCase("Mart") || text.equalsIgnoreCase("Mar")) {
            return MonthOfYear.MARCH;
        }
        if (text.equalsIgnoreCase("Nisan") || text.equalsIgnoreCase("Nis")) {
            return MonthOfYear.APRIL;
        }
        if (text.equalsIgnoreCase("Mayıs") || text.equalsIgnoreCase("May")) {
            return MonthOfYear.MAY;
        }
        if (text.equalsIgnoreCase("Haziran") || text.equalsIgnoreCase("Haz")) {
            return MonthOfYear.JUNE;
        }
        if (text.equalsIgnoreCase("Temmuz") || text.equalsIgnoreCase("Tem")) {
            return MonthOfYear.JULY;
        }
        if (text.equalsIgnoreCase("Ağustos") || text.equalsIgnoreCase("Ağu")) {
            return MonthOfYear.AUGUST;
        }
        if (text.equalsIgnoreCase("Eylül") || text.equalsIgnoreCase("Eyl")) {
            return MonthOfYear.SEPTEMBER;
        }
        if (text.equalsIgnoreCase("Ekim") || text.equalsIgnoreCase("Eki")) {
            return MonthOfYear.OCTOBER;
        }
        if (text.equalsIgnoreCase("Kasım") || text.equalsIgnoreCase("Kas")) {
            return MonthOfYear.NOVEMBER;
        }
        if (text.equalsIgnoreCase("Aralık") || text.equalsIgnoreCase("Ara") || text.equalsIgnoreCase("Aralığ")) {
            return MonthOfYear.DECEMBER;
        }
        return null;
    }

    public static WeekOfMonth getWeekOfMonth(String text) {

        if (text.equalsIgnoreCase("birinci") || text.equalsIgnoreCase("1") || text.equalsIgnoreCase("ilk") ) {
            return WeekOfMonth.FIRST;
        }
        if (text.equalsIgnoreCase("ikinci") || text.equalsIgnoreCase("2")) {
            return WeekOfMonth.SECOND;
        }
        if (text.equalsIgnoreCase("üçüncü") || text.equalsIgnoreCase("3")) {
            return WeekOfMonth.THIRD;
        }
        if (text.equalsIgnoreCase("dördüncü") || text.equalsIgnoreCase("4")) {
            return WeekOfMonth.FOURTH;
        }
        if (text.equalsIgnoreCase("beşinci") || text.equalsIgnoreCase("5")) {
            return WeekOfMonth.FIFTH;
        }

        if (text.equalsIgnoreCase("sonuncu")) {
            return WeekOfMonth.LAST;
        }

        return null;
    }

    public static int getDayOfWeekFromOrder(String text) {

        if (text.equalsIgnoreCase("birinci") || text.equalsIgnoreCase("ilk") || text.equalsIgnoreCase("1")) {
            return 1;
        }
        if (text.equalsIgnoreCase("ikinci") || text.equalsIgnoreCase("2")) {
            return 2;
        }
        if (text.equalsIgnoreCase("üçüncü") || text.equalsIgnoreCase("3")) {
            return 3;
        }
        if (text.equalsIgnoreCase("dördüncü") || text.equalsIgnoreCase("4")) {
            return 4;
        }
        if (text.equalsIgnoreCase("beşinci") || text.equalsIgnoreCase("5")) {
            return 5;
        }

        if (text.equalsIgnoreCase("altıncı") || text.equalsIgnoreCase("6")) {
            return 6;
        }

        if (text.equalsIgnoreCase("yedinci") || text.equalsIgnoreCase("7")) {
            return 7;
        }

        if (text.equalsIgnoreCase("sekizinci") || text.equalsIgnoreCase("8")) {
            return 8;
        }
        if (text.equalsIgnoreCase("dokuzuncu") || text.equalsIgnoreCase("9")) {
            return 9;
        }
        if (text.equalsIgnoreCase("onuncu") || text.equalsIgnoreCase("10")) {
            return 10;
        }
        if (text.equalsIgnoreCase("on birinci") || text.equalsIgnoreCase("11")) {
            return 11;
        }
        if (text.equalsIgnoreCase("on ikinci") || text.equalsIgnoreCase("12")) {
            return 12;
        }
        if (text.equalsIgnoreCase("on üçüncü") || text.equalsIgnoreCase("13")) {
            return 13;
        }
        if (text.equalsIgnoreCase("on dördüncü") || text.equalsIgnoreCase("14")) {
            return 14;
        }
        if (text.equalsIgnoreCase("ön beşinci") || text.equalsIgnoreCase("15")) {
            return 15;
        }
        if (text.equalsIgnoreCase("on altıncı") || text.equalsIgnoreCase("16")) {
            return 16;
        }
        if (text.equalsIgnoreCase("on yedinci") || text.equalsIgnoreCase("17")) {
            return 17;
        }
        if (text.equalsIgnoreCase("on sekizinci") || text.equalsIgnoreCase("18")) {
            return 18;
        }
        if (text.equalsIgnoreCase("on dokuzuncu") || text.equalsIgnoreCase("19")) {
            return 19;
        }

        if (text.equalsIgnoreCase("yirminci") || text.equalsIgnoreCase("20")) {
            return 20;
        }

        if (text.equalsIgnoreCase("yirmi birinci") || text.equalsIgnoreCase("21")) {
            return 21;
        }

        if (text.equalsIgnoreCase("yirmi ikinci") || text.equalsIgnoreCase("22")) {
            return 22;
        }

        if (text.equalsIgnoreCase("yirmi üçüncü") || text.equalsIgnoreCase("23")) {
            return 23;
        }

        if (text.equalsIgnoreCase("yirmi dördüncü") || text.equalsIgnoreCase("24")) {
            return 24;
        }

        if (text.equalsIgnoreCase("yirmi beşinci") || text.equalsIgnoreCase("25")) {
            return 25;
        }
        if (text.equalsIgnoreCase("yirmi altıncı") || text.equalsIgnoreCase("26")) {
            return 26;
        }
        if (text.equalsIgnoreCase("yirmi yedinci") || text.equalsIgnoreCase("27")) {
            return 27;
        }
        if (text.equalsIgnoreCase("yirmi sekizinci") || text.equalsIgnoreCase("28")) {
            return 28;
        }
        if (text.equalsIgnoreCase("yirmi dokuzuncu") || text.equalsIgnoreCase("29")) {
            return 29;
        }
        if (text.equalsIgnoreCase("otuzuncu") || text.equalsIgnoreCase("30")) {
            return 30;
        }
        if (text.equalsIgnoreCase("otuz birinci") || text.equalsIgnoreCase("31")) {
            return 31;
        }

        return 0;
    }

    public static int getIntFromBasicTerm(String text) {

        if (text.equalsIgnoreCase("bir")) {
            return 1;
        }
        if (text.equalsIgnoreCase("iki")) {
            return 2;
        }
        if (text.equalsIgnoreCase("üç")) {
            return 3;
        }
        if (text.equalsIgnoreCase("dört")) {
            return 4;
        }
        if (text.equalsIgnoreCase("beş")) {
            return 5;
        }

        if (text.equalsIgnoreCase("altı")) {
            return 6;
        }

        if (text.equalsIgnoreCase("yedi")) {
            return 7;
        }

        if (text.equalsIgnoreCase("sekiz")) {
            return 8;
        }
        if (text.equalsIgnoreCase("dokuz")) {
            return 9;
        }
        if (text.equalsIgnoreCase("on")) {
            return 10;
        }
        if (text.equalsIgnoreCase("on bir")) {
            return 11;
        }
        if (text.equalsIgnoreCase("on iki")) {
            return 12;
        }
        if (text.equalsIgnoreCase("on üç")) {
            return 13;
        }
        if (text.equalsIgnoreCase("on dört")) {
            return 14;
        }
        if (text.equalsIgnoreCase("on beş")) {
            return 15;
        }
        if (text.equalsIgnoreCase("on altı")) {
            return 16;
        }
        if (text.equalsIgnoreCase("on yedi")) {
            return 17;
        }
        if (text.equalsIgnoreCase("on sekiz")) {
            return 18;
        }
        if (text.equalsIgnoreCase("on dokuz")) {
            return 19;
        }

        if (text.equalsIgnoreCase("yirmi")) {
            return 20;
        }

        if (text.equalsIgnoreCase("yirmi bir")) {
            return 21;
        }

        if (text.equalsIgnoreCase("yirmi iki")) {
            return 22;
        }

        if (text.equalsIgnoreCase("yirmi üç")) {
            return 23;
        }

        if (text.equalsIgnoreCase("yirmi dört")) {
            return 24;
        }

        if (text.equalsIgnoreCase("yirmi beş")) {
            return 25;
        }
        if (text.equalsIgnoreCase("yirmi altı")) {
            return 26;
        }
        if (text.equalsIgnoreCase("yirmi yedi")) {
            return 27;
        }
        if (text.equalsIgnoreCase("yirmi sekiz")) {
            return 28;
        }
        if (text.equalsIgnoreCase("yirmi dokuz")) {
            return 29;
        }
        if (text.equalsIgnoreCase("otuz")) {
            return 30;
        }
        if (text.equalsIgnoreCase("kırk")) {
            return 40;
        }
        if (text.equalsIgnoreCase("elli")) {
            return 50;
        }
        if (text.equalsIgnoreCase("altmış")) {
            return 60;
        }
        if (text.equalsIgnoreCase("yetmiş")) {
            return 70;
        }
        if (text.equalsIgnoreCase("seksen")) {
            return 80;
        }
        if (text.equalsIgnoreCase("doksan")) {
            return 90;
        }
        if (text.equalsIgnoreCase("yüz")) {
            return 100;
        }

        return 0;
    }

    public int getTimeZone(String zone) {
        if (zone.equalsIgnoreCase("PST") || zone.equalsIgnoreCase("Pacific Time")) {
            return +480;
        }

        else if (zone.equalsIgnoreCase("PDT") || zone.equalsIgnoreCase("PT")) {
            return +420;
        }

        else if (zone.equalsIgnoreCase("EST")) {
            return +300;
        }

        else if (zone.equalsIgnoreCase("EDT") || zone.equalsIgnoreCase("ET") || zone.equalsIgnoreCase("Eastern Daylight Time")) {
            return +240;
        }

        else if (zone.equalsIgnoreCase("CST")) {
            return +360;
        } else if (zone.equalsIgnoreCase("CET") || zone.equalsIgnoreCase("Central European Time")) {
            return -60;
        } else if (zone.equalsIgnoreCase("CEST") || zone.equalsIgnoreCase("EET")) {
            return -120;
        }

        else if (zone.equalsIgnoreCase("BST")) {
            return -60;
        }

        else if (zone.equalsIgnoreCase("EEST") || zone.equalsIgnoreCase("Eastern Europe Summer Time")) {
            return -180;
        }

        else if (zone.equalsIgnoreCase("GMT")) {
            return 0;
        } else if (zone.equalsIgnoreCase("IST")) {
            return -60;
        } else if (zone.equalsIgnoreCase("MSD")) {
            return -240;
        } else if (zone.equalsIgnoreCase("MSK")) {
            return -240;
        } else if (zone.equalsIgnoreCase("WEST")) {
            return -60;
        } else if (zone.equalsIgnoreCase("WET")) {
            return 0;
        } else if (zone.equalsIgnoreCase("CST")) {
            return -480;
        } else if (zone.equalsIgnoreCase("HKT")) {
            return -480;
        } else if (zone.equalsIgnoreCase("IDT")) {
            return -180;
        } else if (zone.equalsIgnoreCase("JST")) {
            return -540;
        } else if (zone.equalsIgnoreCase("CST")) {
            return +360;
        } else if (zone.equalsIgnoreCase("EST")) {
            return +300;
        }
        return 0;

    }

    public static int getStringtoInt(String text){
        return Integer.parseInt(text);
    }

    public static TimeTag getTimeTag(String tag) {
        if (tag.contains("after") || tag.contains("past")) {
            return TimeTag.AFTER;
        }
        return TimeTag.BEFORE;
    }

}
