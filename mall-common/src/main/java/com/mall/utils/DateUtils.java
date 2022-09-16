package com.mall.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 */
public class DateUtils {


    static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 返回两个时间之间的差 精确到秒
     *
     * @param olddatetime
     * @param nowtime
     * @return
     */
    public static String DateDifferenceS(String olddatetime, String nowtime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse(olddatetime);
        Date date = format.parse(nowtime);
        long between = date.getTime() - parse.getTime();
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒");
        return s + "";
    }

    /**
     * 返回传入字符串的date  字符串格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 传入时间
     * @return
     */
    public static Date rtuDateByStr(String date) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 返回传入时间的格式化字符串  格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 传入时间
     * @return
     */
    public static String rtuDateH(Date date) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(date);
    }

    /**
     * 返回当前时间　格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String fromDateH() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(new Date());
    }

    public static String fromDateymdhm() {
        DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmm");
        return format2.format(new Date());
    }

    public static String getBeforeMinuteTime(int minute) {
        Date now = new Date();
        Date now_10 = new Date(now.getTime() - 60 * minute); //10分钟前的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String nowTime_10 = dateFormat.format(now_10);
        return nowTime_10;
    }

    /**
     * 获取5分钟前的时间
     */
    public static String getBefore5minTime() {
        Date now = new Date();
        Date now_5 = new Date(now.getTime() - 300000); //10分钟前的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String nowTime_5 = dateFormat.format(now_5);
        return nowTime_5;
    }

    /**
     * 获取当前时间的前几个小时的时间 返回格式 yyyy-MM-dd hh
     *
     * @param ihour
     * @return
     */
    public static String getBeforeHourTime(int ihour) {
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        returnstr = df.format(calendar.getTime());
        return returnstr;
    }

    /**
     * 获取当前时间的前几个小时的时间 返回格式 yyyy-MM-dd HH:mm:ss
     *
     * @param ihour
     * @return
     */
    public static String getBeforeHourTimeAndS(int ihour) {
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        returnstr = df.format(calendar.getTime());
        return returnstr;
    }

    /**
     * 获取当前时间的前几分钟的时间 返回格式 yyyy-MM-dd HH:mm:ss
     *
     * @param minute
     * @return
     */
    public static String getBeforeMinuteTimeAndS(int minute) {
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minute);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        returnstr = df.format(calendar.getTime());
        return returnstr;
    }


    /**
     * 返回当前时间　格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String fromDateY() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(new Date());
    }

    /**
     * 返回当前时间　格式：yyyy-MM-dd HH:mm
     *
     * @return String
     */
    public static String fromDateHM() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format1.format(new Date());
    }

    /**
     * 返回当前月　格式：yyyy-MM
     *
     * @return String
     */
    public static String fromDateMonth() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM");
        return format1.format(new Date());
    }

    /**
     * 返回当前时间　格式：HH:mm
     *
     * @return String
     */
    public static String fromTimemm() {
        DateFormat format1 = new SimpleDateFormat("HH:mm");
        return format1.format(new Date());
    }

    /**
     * 返回前i天的日期　格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String findday(int i) {
        Calendar c = Calendar.getInstance();
        //过去七天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE, -i);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 返回前i天的日期　格式：MM-dd
     *
     * @return String
     */
    public static String finddaynotyear(int i) {
        Calendar c = Calendar.getInstance();
        //过去七天
        SimpleDateFormat format = new SimpleDateFormat("M-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE, -i);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取上月开始时间和结束时间
     */
    public static String[] findMonthSdEd(String year, String month) {
        String[] sded = {};
        Calendar c = Calendar.getInstance();
        if (month == null || year == null) {
            c.add(Calendar.MONTH, -1);
        } else {
            c.setTime(new Date());
            c.set(Calendar.YEAR, Integer.parseInt(year));
            c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);

        //按格式输出
        String gtime = sdf.format(c.getTime()); //上月最后一天
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01 00:00");
        String gtime2 = sdf2.format(c.getTime()); //上月第一天
        sded = (gtime2 + "_" + gtime).split("_");
        return sded;
    }

    public static List<String> yearMonth() {
        LocalDate today = LocalDate.now();
        List<String> monList = new ArrayList<>();
        for (long i = 1L; i <= 12L; i++) {
            LocalDate localDate = today.minusMonths(i);
            String ss = localDate.toString().substring(0, 7);
            monList.add(ss);
        }
        return monList;
    }


}
