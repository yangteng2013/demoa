package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        testfirst();
//        testCal();
//        test2List();

//        testSet();

        testDupicate();
    }

    private static void testDupicate() {
        int[] a = {1, 2, 5, 5, 6, 6, 7, 2, 9, 2};
        int count = 0;
        for (int j = 0; j < a.length; j++) {
            for (int k = j + 1; k < a.length; k++) {
                if (a[j] == a[k]) {
                    count++;
                }
            }
            if (count == 1)
                System.out.println("重复元素 : " + a[j]);
            count = 0;
        }
    }


    private static void testSet() {
        List<String> ts = new ArrayList<>();
        ts.add("悦动力1号换机油套餐");
        ts.add("悦动力1号换机油套餐");
        ts.add("2019夏季保");
        ts.add("810配件");
        ts.add("悦动力1号换机油套餐");
        ts.add("2019夏季保");
        ts.add("精洗打蜡套餐卡");
        ts.add("空调四合一清洁杀菌养护套餐");

        System.out.println(ts);

        System.out.println(delRepeat(ts));

    }

    // 遍历后判断赋给另一个list集合，保持原来顺序
    public static List<String> delRepeat(List<String> list) {
        if (list == null || list.size() <= 0) {
            return new ArrayList<>();
        }
        List<String> listNew = new ArrayList<String>();
        for (String str : list) {
            if (!listNew.contains(str)) {
                listNew.add(str);
            }
        }
        return listNew;
    }

    private static void test2List() {

        List<Student> studentList1 = new ArrayList<>();
        List<Student> studentList2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                studentList1.add(new Student("100" + i, "Jim" + i));
            } else {
                studentList1.add(new Student("300" + i, "Jim" + i));
            }
        }

        for (int i = 0; i < 10; i++) {
            studentList2.add(new Student("100" + i, "Jame" + i, 18 + i));
        }


        List<Student> studentList = new ArrayList<>();
        studentList.addAll(studentList1);

        studentList.removeAll(studentList2);
        for (Student student : studentList) {
            System.out.println(student.getId());
        }
        studentList.addAll(studentList2);
        studentList.removeAll(studentList1);
        for (Student student : studentList) {
            System.out.println(student.getId());
        }


    }

    private static void testfirst() {

//        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");

//
////        System.out.println("当天24点时间：" + getTimesnight().toLocaleString());
//        System.out.println("当前时间：" + shortSdf.format(new Date()));
////        System.out.println("当天0点时间：" + getTimesmorning().toLocaleString());
////        System.out.println("昨天0点时间：" + getYesterdaymorning().toLocaleString());
////        System.out.println("近7天时间：" + getWeekFromNow().toLocaleString());
////        System.out.println("本周周一0点时间：" + getTimesWeekmorning().toLocaleString());
////        System.out.println("本周周日24点时间：" + getTimesWeeknight().toLocaleString());
//        System.out.println("本月初0点时间：" + shortSdf.format(getTimesMonthmorning()));
////        System.out.println("本月未24点时间：" + getTimesMonthnight().toLocaleString());
////        System.out.println("上月初0点时间：" + getLastMonthStartMorning().toLocaleString());
////        System.out.println("本季度开始点时间：" + getCurrentQuarterStartTime().toLocaleString());
////        System.out.println("本季度结束点时间：" + getCurrentQuarterEndTime().toLocaleString());
////        System.out.println("本年开始点时间：" + getCurrentYearStartTime().toLocaleString());
////        System.out.println("本年结束点时间：" + getCurrentYearEndTime().toLocaleString());
////        System.out.println("上年开始点时间：" + getLastYearStartTime().toLocaleString());
//
//
//        List<String> oled = new ArrayList<>();
//        oled.add("One");
//        oled.add("Two");
//        oled.add("Three");
//        oled.add("Four");
//        oled.add("Five");
//
//        System.out.println(oled);
//
//        oled.set(2,"Hello");
//        System.out.println(oled);
//
//
//        String mileage = "1238KM";
//        String cm = mileage.substring(0,mileage.indexOf("KM"));
//        System.out.println(cm);
//
//
//        double d1 = 109.234;
//        float f1 = 102.23f;
//        int i1 = (int) d1;
//        int i2 = (int) f1;
//
//        String strs= "176.20";
//
//        int sscode = (int) Double.parseDouble(strs);
//        System.out.println("sscode="+sscode);
//
//        System.out.println(89/100.0);
//
//        System.out.println(i1 + "  " + i2);
//
//        double celld = 879.98/1000*100;
//        System.out.println(celld);
//        System.out.println(Math.round(celld));

    }

    private static void testCal() {
//        double f = 11232.1233;
//        BigDecimal bg = new BigDecimal(f);
//        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
//        System.out.println(isNumeric(String.valueOf(f)));
//
//        System.out.println(Cal.reserved2("255.556"));
//        System.out.println(Cal.reservedInt("255.556"));
//        System.out.println(Cal.calDiscountRate(88,103));
//        System.out.println(Cal.calDiscountRate("88","103"));
//        System.out.println(Cal.calDiscountedPrice(103,85));
//        System.out.println(Cal.calDiscountRate(87.55,103));
//        System.out.println(Cal.calDiscountedPrice("103","85"));
//
//
//        System.out.println(Cal.calItemSalePrice(12,88,3));
//
//        System.out.println(Cal.reserved2("125.5677"));
        String sNo = "1";

        System.out.println(Cal.reserved2(sNo) + "");
        System.out.println(Cal.reserved2("-2") + "");

//        String dates = "2020-8-18 10:57:47";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = new Date(dates);
//
////        Calendar calendar = Calendar.getInstance();
////        Date date = new Date(); // date 包括时分秒
//        String s = sdf.format(date1); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
//        try {
//            Date date2 = sdf.parse(s); // 把上面的字符串解析为日期类型
//            System.out.println(sdf.format(date2));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        System.out.println(Cal.reserved2("1.0"));
        System.out.println(Cal.reserved2("-3.0"));
//       // 方法一：String的format方法（推荐）-
//
//        double f2 = 12345678.5585;
//
//        System.out.println(String.format("%.2f", f2));
//
//
//
//        //方法二：DecimalFormat的format方法
//
//        double f3 = 12345678;
//
//        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(f3));


    }

    /*是否为数字*/
    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9.]*");

        Matcher isNum = pattern.matcher(str);

        return isNum.matches();

    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();


    }

    // 获得昨天0点时间
    public static Date getYesterdaymorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    // 获得当天近7天时间
    public static Date getWeekFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000 * 7);
        return cal.getTime();
    }

    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    public static Date getLastMonthStartMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesMonthmorning());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }


    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
        return cal.getTime();
    }

    public static Date getCurrentYearEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public static Date getLastYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }
}
