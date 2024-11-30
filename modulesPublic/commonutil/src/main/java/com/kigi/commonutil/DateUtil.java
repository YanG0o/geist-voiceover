package com.kigi.commonutil;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 *
 */
public class DateUtil {


    private static String mYear; // 当前年
    private static String mMonth; // 月
    private static String mDay;
    private static String mWay;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

    public final static String CONST_DATE_DAY_FORMAT = "yyyy/MM/dd";
    public final static String CONST_DAY_ = "dd";
    public final static String CONST_DATE_DAY_FORMAT_RUNG = "yyyy-MM-dd";
    public final static String CONST_DATE_FORMAT_WITH_CHINESE = "yyyy年MM月dd日";
    public final static String CONST_DATE_FORMAT_WITH_CHINESE_NOYEAR = "MM月dd日";
    public final static String CONST_DATE_FORMAT_WITH_CHINESE_NOYEAR_NOZERO = "M月dd日";
    public final static String CONST_DATE_FORMAT_WITH_CHINESE_HHMM = "HH:mm";
    public final static String CONST_DATE_FORMAT_WITH_CHINEST_MMDDHHMM = "MM月dd日 HH:mm";


    public final static String CONST_NAME_TIME = "yyyyMMddHHmmssSSS";
    public final static String CONST_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public final static String CONST_DATE_TIME_FORMAT2 = "yyyy-MMdd HH:mm:ss";
    public final static String CONST_DATE_TIME_FORMAT3 = "yyyy-MM-dd HH:mm:ss";
    public final static String CONST_DATE_TIME_FORMAT5 = "MM-dd HH:mm";
    public final static String CONST_DATE_TIME_FORMAT4 = "yyyy-MM-dd HH:mm";
    public final static String DATE_YEAR_MONTH_RUNG = "yyyy-MM";
    /**
     * 日期格式：yyyy/MM
     */
    public final static String CONST_DATE_FORMAT_VMON = "yyyy/MM";
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdfForMonths = new SimpleDateFormat("yyyy/MM");
    @SuppressLint("SimpleDateFormat")
    public final static SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);

    /**
     * @param date yyyy-mm-dd hh:mm:ss
     * @return date string
     */
    public static String getMMDD_Week_HHmm(String date) {
        Date d = ConvertToDate(date);
        return fomatDate2String(d, CONST_DATE_FORMAT_WITH_CHINESE_NOYEAR) + " " + getWeekOfDate(d) + " " + fomatDate2String(d, CONST_DATE_FORMAT_WITH_CHINESE_HHMM);
    }

    /**
     * 获取当前日期几月几号
     */
    public static String getDateString() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mMonth + "月" + mDay + "日";
    }

    /**
     * 获得下一个月
     *
     * @return
     * @Time 2015年2月27日 22:33:53
     */
    public static String getDataNextForMonths(String mMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StringToDateMon(mMonth));
        calendar.add(Calendar.MONTH, +1);
        Date date = calendar.getTime();
        return sdfForMonths.format(date);
    }

    /**
     * 获得月份
     *
     * @return
     * @Time 2015年2月27日 22:33:53
     */
    public static String getMonths(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, num);
        Date date = calendar.getTime();
        return sdfForMonths.format(date);
    }

    /**
     * 获得当前一个月
     *
     * @return
     */
    public static String getLastForMonths(String mMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StringToDateMon(mMonth));
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        return sdfForMonths.format(date);
    }

    /**
     * 获取当前的日期
     * yyyy/MM/dd
     *
     * @return
     */
    public static String getDateDayNow(String dataFormat) {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);
        return format.format(new Date());
    }

    /**
     * 格式化日期
     *
     * @param date    日期
     * @param pattern 格式
     * @return String
     */
    public static String fomatDate2String(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 获取当前是几号
     * yyyy/MM/dd
     *
     * @return
     */
    public static String getDayNowRung() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT_RUNG);
        return format.format(new Date());
    }

    /**
     * 获取当前的日期
     * yyyy-MM-dd
     *
     * @return
     */
    public static String getDateDayNowRung() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT_RUNG);
        return format.format(new Date());
    }

    /**
     * 获取yyyyMMddhhmmss格式时间
     *
     * @return
     */
    public static String getDateTimeName() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_NAME_TIME);
        return format.format(new Date());
    }

    /**
     * 获得当前时间后N天
     *
     * @return
     */
    public static Date getDayForNdayDate(Date day, int afterDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, afterDay);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前的精准时间
     * yyyy/MM/dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTimeNow() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_TIME_FORMAT);
        return format.format(new Date());
    }

    /**
     * 获取当前的精准时间
     * yyyy-MMdd HH:mm:ss
     *
     * @return
     */
    public static String getDateTimeNow2() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_TIME_FORMAT2);
        return format.format(new Date());
    }

    /**
     * 获取当前的精准时间
     *
     * @retur
     */
    public static String getDateTimeNow3() {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_TIME_FORMAT3);
        return format.format(new Date());
    }

    /**
     *
     */
    public static String formatYYMMDDHHMM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_TIME_FORMAT4);
        return format.format(date);
    }

    /**
     *
     */
    public static String formatYYMMDDHHMMSS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_TIME_FORMAT3);
        return format.format(date);
    }

    /**
     *
     */
    public static String ConverToMMDD_HHMM(String d) {
        return fomatDate2String(ConvertToDate(d), CONST_DATE_FORMAT_WITH_CHINEST_MMDDHHMM);
    }

    /**
     *
     */
    public static String ConverToMMDD(String d) {
        return fomatDate2String(ConvertToDate(d), CONST_DATE_FORMAT_WITH_CHINESE_NOYEAR);
    }

    /**
     * 获得当前时间后N天
     *
     * @return
     */
    public static String getDayForNday(Date day, int afterDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, afterDay);
        Date date = calendar.getTime();
        SimpleDateFormat sdf2 = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);
        return sdf2.format(date);
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 转换时间格式
     *
     * @return
     */
    public static String getDateDayString(Date dateDay) {
        SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);
        return format.format(dateDay);
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {


        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }

    /**
     * String转换为Date
     */
    public static Date StringToDateMon(String s) {
        Date time = new Date();
        DateFormat sdf = null;
        s = s.replace("T", " ");
        if (s.contains("/")) {
            sdf = new SimpleDateFormat(CONST_DATE_FORMAT_VMON,
                    Locale.CHINA);
        } else if (s.contains("-")) {
            s = s.replace("-", "/");
            sdf = new SimpleDateFormat(CONST_DATE_FORMAT_VMON,
                    Locale.CHINA);
        }
        try {
            if (sdf != null) {
                time = sdf.parse(s);
            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return time;
    }

    /**
     * 获得形如  mm月dd日 周五 hh:mm 的日期
     */
    public static String ConverToMMDDWeekHHMM(String date) {
        String var1 = DateUtil.fomatDate2String(DateUtil.ConvertToDate(date), CONST_DATE_FORMAT_WITH_CHINEST_MMDDHHMM);
        return var1.split(" ")[0] + " " + getWeekOfDate(DateUtil.ConvertToDate(date)) + " " + var1.split(" ")[1];
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        try {


            c.setTime(format.parse(time));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 获得指定时间前后N天
     *
     * @return
     */
    public static String getDayForNday(String day, int N) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(day));
            if (N > 0)
                calendar.add(Calendar.DAY_OF_MONTH, +N);
            if (N < 0)
                calendar.add(Calendar.DAY_OF_MONTH, N);
            Date date = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 获得指定时间 Date
     *
     * @return
     */
    public static Date getSpecifiedDate(String day, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(day));
            date = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得指定时间 Calendar
     *
     * @return
     */
    public static Calendar getSpecifiedCalendar(String day, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(day));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * 比较两个日期
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @return 返回较大日期
     */
    public static String setDateCompareString(String date1, String date2) {
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2.getTime() - d1.getTime() > 0 ? date2 : format.format(d1);
    }

    /**
     * 比较两个日期
     *
     * @param date1 yyyy/MM/dd
     * @param date2 yyyy/MM/dd
     * @return 返回较大日期
     */
    public static String setDateCompareString1(String date1, String date2) {
        Date d1 = new Date();
        Date d2 = new Date();
        if (TextUtils.isEmpty(date1) && TextUtils.isEmpty(date2))
            return "";
        if (TextUtils.isEmpty(date1) && !TextUtils.isEmpty(date2))
            return date2;
        if (!TextUtils.isEmpty(date1) && TextUtils.isEmpty(date2))
            return date1;
        try {
            d1 = format.parse(date1.replaceAll("-", "/"));
            d2 = format.parse(date2.replaceAll("-", "/"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2.getTime() - d1.getTime() > 0 ? date2 : format.format(d1);
    }

    /**
     * 比较两个日期
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @return 返回布尔值
     */
    public static boolean getDateCompare(String date1, String date2) {
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2.getTime() - d1.getTime() > 0 ? false : true;
    }


    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static List<String> getSevendate() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));


        for (int i = 0; i < 7; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1))) {
                mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1)));
            }
            String date = mMonth + "月" + mDay + "日";
            dates.add(date);
        }
        return dates;
    }


    /**
     * 获取今天往后一周的集合
     */
    public static List<String> get7week() {
        String week = "";
        List<String> weeksList = new ArrayList<String>();
        List<String> dateList = get7date();
        for (String s : dateList) {
            if (s.equals(StringData())) {
                week = "今天";
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }


    public static List<String> get7date() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sim = new SimpleDateFormat(
                "yyyy/MM/dd");
        String date = sim.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            date = sim.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }

    /**
     * '-'转‘/’
     */
    public static String StringToDateForS(String s) {
        Date time = new Date();
        DateFormat sdf = null;
        s = s.replace("-", "/");
        sdf = new SimpleDateFormat(CONST_DATE_DAY_FORMAT, Locale.CHINA);
        try {
            return sdf.format(sdf.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return s;
    }

    /**
     * 设置时间
     *
     * @param sCurrDate
     * @return
     */
    public static Calendar CalendarSetTime(String sCurrDate) {
        Calendar oCalendar = Calendar.getInstance();
        Date oDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (!sCurrDate.equals("") && sCurrDate != null) {
            try {
                oCalendar.setTime(sdf.parse(sCurrDate));
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
//                System.out.println("日期格式转换错误：=" + sCurrDate);
            }
        } else
            oCalendar.setTime(oDate);
        return oCalendar;
    }

    /**
     * 获得当前日期的本周及其他日期
     *
     * @param sCurrDate
     * @return
     */
    public static List Calendar(String sCurrDate) {
        String[] oneWeekDay = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar oCal = null;
        String LoopDate = "";
        int iweek = 0, iCurrWeek = 0;
        oCal = CalendarSetTime(sCurrDate);//设置时间
        iweek = oCal.get(Calendar.DAY_OF_WEEK) - 1;
        String DataSet[] = null;
        ArrayList<String[]> DayAndWeek = new ArrayList();
        for (int i = iweek; i > 0; i--) {
            oCal.add(Calendar.DATE, -i);
            LoopDate = oCal.get(Calendar.YEAR) + "-" + complementDate((oCal.get(Calendar.MONTH) + 1)) + "-" + complementDate(oCal.get(Calendar.DATE));
            oCal = CalendarSetTime(sCurrDate);//重置时间
            DataSet = new String[2];
            DataSet[0] = LoopDate;
            DataSet[1] = oneWeekDay[(iCurrWeek++)];
            DayAndWeek.add(DataSet);
        }
        for (int i = 0; i < 7 - iweek; i++) {
            oCal.add(Calendar.DATE, i);
            LoopDate = oCal.get(Calendar.YEAR) + "-" + complementDate((oCal.get(Calendar.MONTH) + 1)) + "-" + complementDate(oCal.get(Calendar.DATE));
            oCal = CalendarSetTime(sCurrDate);
            DataSet = new String[2];
            DataSet[0] = LoopDate;
            DataSet[1] = oneWeekDay[(iCurrWeek++)];
            DayAndWeek.add(DataSet);
        }
        return DayAndWeek;
    }


    /**
     * 补全时间
     */
    public static String complementDate(int date) {
        String cDate = date + "";
        if (date < 10)
            cDate = "0" + cDate;

        return cDate;
    }

    public static Date convertToDateYYYYMMDD(String date) {
        try {

            return sdf3.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date ConvertToDate(String date) {
        try {

            return sdf2.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Date();
        }
    }

    public static String ConvertyyyyMMddHHmmss(String date) {
        try {
            Date d = sdf.parse(date);
            return sdf.format(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 比较两个日期大小
     *
     * @param lastDate
     * @param nextDate
     * @return
     */
    public static boolean compare_date(String lastDate, String nextDate) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            if (df.parse(lastDate).getTime() > df.parse(nextDate).getTime()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDataNow(String s) {
        SimpleDateFormat format = new SimpleDateFormat(s);
        return format.format(new Date());
    }

    /**
     * 转换时间格式
     *
     * @return
     */
    public static String getFormatPOSMDate(String dateStr) {
//        String parStr = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);
            SimpleDateFormat format = new SimpleDateFormat(CONST_DATE_DAY_FORMAT);
            dateStr = format.format(date);
        } catch (ParseException e) {
//            e.printStackTrace();
            try {
                if (dateStr != null && dateStr.contains("-")) {
                    return dateStr.split(" ")[0].replaceAll("-", "/");
                }
            } catch (Exception e1) {
//                e1.printStackTrace();
                return dateStr.replaceAll("-", "/");
            }
        }
        return dateStr;
    }

    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cal.getTime());
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return format.format(cal.getTime());
    }

    /**
     * 格式化日期
     *
     * @param time 时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String format2yyMMdd(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(CONST_DATE_DAY_FORMAT_RUNG);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        Log.e("format2yyMMdd", System.currentTimeMillis() + "");
        Log.e("format2yyMMdd", "time = " + time);

//        Date specifiedDate = getSpecifiedDate("2023-01-16", CONST_DATE_DAY_FORMAT_RUNG);
//        Log.e("format2yyMMdd","specifiedDate = "+specifiedDate.getTime());
        return sdf.format(cal.getTime());
    }

    /**
     * 格式化日期
     *
     * @param time 时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String format2MMddHHmm(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(CONST_DATE_TIME_FORMAT5);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        Log.e("format2yyMMdd", System.currentTimeMillis() + "");
        Log.e("format2yyMMdd", "time = " + time);

//        Date specifiedDate = getSpecifiedDate("2023-01-16", CONST_DATE_DAY_FORMAT_RUNG);
//        Log.e("format2yyMMdd","specifiedDate = "+specifiedDate.getTime());
        return sdf.format(cal.getTime());
    }

    /**
     * 获得指定时间前后N天
     *
     * @return
     */
    public static long getDateForNYear(int N) {
        try {
            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(format.parse(day));
            if (N > 0)
                calendar.add(Calendar.YEAR, +N);
            if (N < 0)
                calendar.add(Calendar.YEAR, N);
            return calendar.getTime().getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() / 1000;
    }

    public static String convertDate(String utcStr) {
        try {
            String dp = "EEE MMM dd HH:mm:ss zzz yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(dp,Locale.ENGLISH);
            Date date = sdf.parse(utcStr);
            return sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return sdf2.format(new Date()) ;
        }
    }
}