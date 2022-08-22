package com.bezkoder.springjwt.constants;

public class TemporalConstants {

    public static final String DAY_OF_WEEK = "(pazar|pazartesi|salı|çarşamba|perşembe|cuma|cumartesi)";
    public static final String DAY_OF_WEEK_EASY = "(paz|pte|sal|çar|per|cum|cte)";
    public static final String SEASON = "(sonbahar|kış|yaz|ilkbahar|bahar)";
    public static final String MONTH_OF_YEAR = "(ocak|ocağ|şubat|mart|nisan|mayıs|haziran|temmuz|ağustos|eylül|ekim|kasım|aralık|aralığ)";
    //public static final String MONTH_OF_YEAR = "((oca(k|ğ))|şubat|mart|nisan|mayıs|haziran|temmuz|ağustos|eylül|ekim|kasım|(aralı(k|ğ))";
    public static final String MONTH_OF_YEAR_EASY = "(oca|şub|mar|nis|may|haz|tem|ağu|eyl|eki|kas|ara)";
  public static final String HOLIDAYS =
      "((Cadılar Bayramı)|(Noel Arifesi)|(Noel Günü)|(Noel)|(Yılbaşı)|(Yılbaşı Arifesi)|(Bağımsızlık Günü)|(Şükran Günü)|(Gaziler Günü)|(İşçi Bayramı)|(Anma Günü)|(Açılış Günü)|(Sevgililer Günü)[\\s]*(günü)?)";
    public static final String BASIC_ORDER = "(birinci|ikinci|üçüncü|dördüncü|beşinci|altıncı|yedinci|sekizinci|dokuzuncu|onuncu|on birinci|on ikinci|on üçüncü|on dördüncü|on beşinci|on altıncı|on yedinci|on sekizinci|on dokuzuncu|yirminci|yirmi birinci|yirmi ikinci|yirmi üçüncü|yirmi dördüncü|yirmi beşinci|yirmi altıncı|yirmi yedinci|yirmi sekizinci|yirmi dokuzuncu|otuzuncu|otuz birinci|kırkinci|ellinci|altmışıncı|yetmişinci|seksen|dokuzuncu|yüzüncü|son)";
    public static final String TIME_ZONE = "((PST|(Pacific Time))|BST|PT|EST|EET|(EEST|(Eastern Europe Summer Time))|ET|(EDT|Eastern Daylight Time)|CST|(CET|Central European Time)|(CEST|Central European Summer Time)|BST|PDT|GMT|UTC|IST|MSD|MSK|WEST|WET|JST|IDT|HKT)";
    public static final String TIME_OF_DAY = "((sabah)|(öğleden sonra)|(akşam)|(gece)|(öğle)|(gece yarısı)|(öğle yemeği zamanı))";
    public static final String DURATION = "(saniye|sn|dakika|dak|dk|saat|sa|hafta|ay|yıl|sene|gün|gece)";
    public static final String BASIC_NUMBER_ONE_TEN = "(bir|iki|üç|dört|beş|altı|altıncı|yedi|sekiz|dokuz|on)";
    public static final String BASIC_NUMBER_ELEVEN_NINETEEN = "((on bir)|(on iki)|(on üç)|(on dört)|(on beş)|(on altı)|(on yedi)|(on sekiz)|(on dokuz))";
    public static final String BASIC_NUMBER_TWENTY_HUNDRED = "(yirmi|otuz|kırk|elli|altmış|yetmiş|seksen|doksan|yüz)";
}

