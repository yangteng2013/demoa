package com.company;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 转换 计算工具类
 * double float 都是默认四舍五入的保留2位
 */
public class Cal {


    /**
     * 计算每一项的价格 优惠后的售价价格 即实际应付金额
     * 原件（原单价）*优惠率*数量或工时
     *
     * @param originPrice
     * @param rate
     * @param num
     * @return
     */
    public static double calItemSalePrice(String originPrice, String rate, String num) {
        if (isBlank(originPrice)) {
            return 0;
        }
        if (isBlank(num)) {
            return 0;
        }
        if (!isBigDecimal(originPrice)) {
            log("originPrice=" + originPrice);
            return 0;
        }
        if (!isBigDecimal(num)) {
            log("num=" + num);
            return 0;
        }
        int cRate = 100;
        if (isBlank(rate) || !isBigDecimal(rate)) {
            cRate = 100;
        } else {
            cRate = reservedInt(rate);
        }
        return calItemSalePrice(reserved2(originPrice), cRate, reserved2(num));
    }

    /**
     * 计算每一项的价格 优惠后的售价价格 即实际应付金额
     * 原件（原单价）*优惠率*数量或工时
     *
     * @param originPrice
     * @param rate
     * @param num
     * @return
     */
    public static double calItemSalePrice(double originPrice, int rate, double num) {
        if (originPrice <= 0) {
            return 0;
        }
        if (num <= 0) {
            return 0;
        }
        if (rate <= 0) {
            rate = 100;
        }
        double salePrice = originPrice * rate / 100.00 * num;
        return reserved2(salePrice);
    }

    /**
     * 优惠后的价格
     *
     * @param originPrice 原价(一般是是指原单价unitPrice,salePrice)
     * @param rate        优惠率
     * @return
     */
    public static double calDiscountedPrice(String originPrice, String rate) {
        if (isBlank(originPrice)) {
            return 0;
        }
        if (!isBigDecimal(originPrice)) {
            log("originPrice=" + originPrice);
            return 0;
        }
        int cRate = 100;
        if (isBlank(rate) || !isBigDecimal(rate)) {
            cRate = 100;
        } else {
            cRate = reservedInt(rate);
        }

        //优惠率是不能少于等于0的，如果是的话，则默认为没有优惠了
        if (cRate <= 0) {
            cRate = 100;
        }

//        double oriPrice = reserved2(originPrice);
//        double disedPrice = oriPrice*cRate/100.00;
//        return reserved2(disedPrice);
        return calDiscountedPrice(reserved2(originPrice), cRate);
    }

    /**
     * 优惠后的价格
     *
     * @param originPrice 原价(一般是是指原单价unitPrice,salePrice)
     * @param rate        优惠率
     * @return
     */
    public static double calDiscountedPrice(double originPrice, int rate) {
        if (originPrice <= 0) {
            return 0;
        }
        if (rate <= 0) {
            rate = 100;
        }
        double disedPrice = originPrice * rate / 100.00;
        return reserved2(disedPrice);
    }

    /**
     * 计算优惠率
     * 优惠后的价格/原价*100
     *
     * @param discountedPrice 优惠后的价格
     * @param orginPrice      原价
     * @return
     */
    public static int calDiscountRate(double discountedPrice, double orginPrice) {
        if (discountedPrice > 0 && orginPrice > 0) {
            return reservedInt(discountedPrice / orginPrice * 100);
        }
        return 0;
    }

    /**
     * 计算优惠率
     * 优惠后的价格/原价*100
     *
     * @param discountedPrice 优惠后的价格
     * @param orginPrice      原价
     * @return
     */
    public static int calDiscountRate(String discountedPrice, String orginPrice) {
        if (isBlank(discountedPrice)) {
            return 0;
        }
        if (isBlank(orginPrice)) {
            return 0;
        }
        if (!isBigDecimal(discountedPrice)) {
            log("discountedPrice:" + discountedPrice);
            return 0;
        }
        if (!isBigDecimal(orginPrice)) {
            log("orginPrice:" + orginPrice);
            return 0;
        }
        double disedPrice = reserved2(discountedPrice);
        double orgPrice = reserved2(orginPrice);
        if (disedPrice > 0 && orgPrice > 0) {
            return reservedInt(disedPrice / orgPrice * 100);
        }
        return 0;
    }

    private static void log(String s) {
        System.out.println(s);
    }

    public static double reserved2(double value) {
        System.out.println("double value=" + value);
        DecimalFormat df = new DecimalFormat("#.00");
        String sValue = df.format(value);
        System.out.println("sValue:" + sValue);
        return Double.parseDouble(sValue);
    }

    public static double reserved2(String value) {
        if (isBlank(value) || !isBigDecimal(value)) {
            return 0;
        }
        System.out.println(value);
        return reserved2(Double.parseDouble(value));
    }

    public static int reservedInt(String value) {
        if (isBlank(value) || !isBigDecimal(value)) {
            return 0;
        }
        BigDecimal bg = new BigDecimal(value);
        double f1 = bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return (int) f1;
    }

    public static int reservedInt(double value) {
        if (value <= 0) {
            return 0;
        }
        BigDecimal bg = new BigDecimal(value);
        double f1 = bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return (int) f1;
    }

    public static int reservedInt(float value) {
        if (value <= 0) {
            return 0;
        }
        BigDecimal bg = new BigDecimal(value);
        double f1 = bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return (int) f1;
    }

    /*是否为数字.*/
    public static boolean isBigDecimal(String str) {
        if (isBlank(str)) {
            return false;
        }
//        Pattern pattern = Pattern.compile("^[\\-|0-9][0-9]*");
        Pattern pattern = Pattern.compile("-?[0-9]*.?[0-9]*");

        Matcher isNum = pattern.matcher(str);

        return isNum.matches();

    }

    /**
     * 字符串数据 保留2位 并返回String
     *
     * @param value
     * @return
     */
    public static String reserved2Str(String value) {
        if (isBlank(value)) {
            return "0";
        }
        if (!isBigDecimal(value)) {
            log("value:" + value);
            return value;
        }
        return String.format("%.2f", value);
    }

    public static boolean isBlank(String value) {
        if (value == null) {
            return true;
        }
        if (value.length() == 0) {
            return true;
        }
        if (value.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
