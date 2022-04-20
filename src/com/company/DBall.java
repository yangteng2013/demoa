package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * https://kaijiang.78500.cn/ssq/
 * <p>
 * 历史比较器：
 * https://www.78500.cn/tool/ssqdb.html
 */
public class DBall {
    static List<List<Integer>> hadAl = new ArrayList<List<Integer>>();

    public static void main(String[] args) {

        init();

    }

    private static void init() {
        List<Integer> blues = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            blues.add(i + 1);
        }
        System.out.println(blues);
        List<Integer> reds = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            reds.add(i + 1);
        }
        System.out.println(reds);


        List<DoubleBallBean> curCounts = genComsphere(2000);
//        System.out.println(curCounts);

        boolean isAllOddOrEven = isAllOddOrEven(curCounts);
        System.out.println("isAllOddOrEven=" + isAllOddOrEven);

        boolean isRegularChange = isRegularChange(curCounts);
        System.out.println("isRegularChange=" + isRegularChange);

        List<List<Integer>> lastGoal = new ArrayList<>();
        List<Integer> temps = new ArrayList<>();
        //01 05 07 14 18 33
        temps.add(1);//06 08 11 22 25 33
        temps.add(5);//06 08 11 22 25 33
        temps.add(7);//06 08 11 22 25 33
        temps.add(14);//06 08 11 22 25 33
        temps.add(18);//06 08 11 22 25 33
        temps.add(33);//06 08 11 22 25 33
        lastGoal.add(temps);


        //1.把最近100期的数据 进行剔除
        checkHadGoal(curCounts);
        System.out.println("curCounts=" + curCounts.size());

        //2.上一期的任意4个 不会出现在现在新的组合中
        checkHadGoal(hadAl, curCounts);

        //遍历 若存在2个相同
        checkCurBalls(8, 25, curCounts);
        checkCurBalls(11, 22, curCounts);
        checkCurBalls(11, 33, curCounts);
        checkCurBalls(22, 33, curCounts);
        checkLasGoal(lastGoal, curCounts);

//        System.out.println(curCounts);
        System.out.println(curCounts.size());


    }


    private static void checkCurBalls(int i, int i1, List<DoubleBallBean> curCounts) {

        Iterator iterator = curCounts.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean cItem = (DoubleBallBean) iterator.next();
            int itemCount = 0;
            for (Integer integer : cItem.blueBalls) {
                if (integer.equals(i) || integer.equals(i1)) {
                    itemCount++;
                }
            }
            if (itemCount == 2) {
                if (iterator.hasNext()) {
                    iterator.remove();
                }
            }


        }


    }

    private static void checkHadGoal(List<DoubleBallBean> curCounts) {
//        List<String> res = txt2String(new File("D:/ideaworkspace/demoa/src/com/company/abc.txt"));
        List<String> res = txt2String(new File("src/com/company/abc.txt"));
//        System.out.println(res.toString());

        for (String re : res) {
            String[] arrays = re.split(System.lineSeparator());
            if (arrays.length > 1) {
                String zero = arrays[0];
                String one = arrays[1];
                List<Integer> cc = new ArrayList<>();
                String[] ones = one.split(" ");
                for (int i = 0; i < ones.length; i++) {
                    cc.add(Integer.valueOf(ones[i]));
                }
                DBall.hadAl.add(cc);
            }
        }
        System.out.println("hadAl=" + DBall.hadAl);
        compareWith(DBall.hadAl, curCounts);
    }

    /**
     * @param hadAl     最近开奖记录
     * @param curCounts 本地随机抽出
     */
    private static void compareWith(List<List<Integer>> hadAl, List<DoubleBallBean> curCounts) {
        Iterator iterator = curCounts.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean items = (DoubleBallBean) iterator.next();
            for (List<Integer> integers : hadAl) {
                boolean equals = compareWithItem(items.blueBalls, integers);
                if (equals) {
                    System.out.println("compareWith items:" + items);
                    iterator.remove();
                }
            }
        }
    }

    private static boolean compareWithItem(List<Integer> items, List<Integer> integers) {
        int count = 0;
        for (Integer item : items) {
            for (Integer integer : integers) {
                if (item.equals(integer)) {
                    count++;
                }
            }
        }
        return count == 6;
    }

    /**
     * 有规律的改变
     * 如 1 3 5 7 9
     * 1 6 11 16 21 26
     * 2 5 8 11 14 17
     *
     * @param curCounts
     * @return
     */
    private static boolean isRegularChange(List<DoubleBallBean> curCounts) {

        Iterator iterator = curCounts.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean items = (DoubleBallBean) iterator.next();
            Set<Integer> cap = new HashSet<>();
            for (int i = items.blueBalls.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    break;
                }
                int a = items.blueBalls.get(i) - items.blueBalls.get(i - 1);
                cap.add(a);
            }
            if (cap.size() == 1) {
                System.out.println("有规律的组合：" + items);
            }
//            else {
//                System.out.println("原组合："+items);
//                System.out.println("无规律的组合"+cap);
//            }

        }


        return false;
    }

    private static boolean isAllOddOrEven(List<DoubleBallBean> curCounts) {

        boolean isAll = false;
        int maxOddOrEvenCount = 4;
        int maxRangeCount = 4;
        Iterator iterator = curCounts.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean items = (DoubleBallBean) iterator.next();
            int count = 0;
            //全是大中小某一个区间值
            int rangeSmall = 0;
            int rangeMid = 0;
            int rangeBig = 0;

            for (Integer item : items.blueBalls) {
                if (item % 2 == 0) {
                    count++;
                }
                if (item <= 11) {
                    rangeSmall++;
                }
                if (item > 11 && item <= 22) {
                    rangeMid++;
                }
                if (item > 22) {
                    rangeBig++;
                }
            }
            if (count > maxOddOrEvenCount || count == 0) {
//                System.out.println("全是期数或偶数的组合："+items);
//                System.out.println(curCounts.size());
                iterator.remove();
//                System.out.println(curCounts.size());
                isAll = true;
            }
            if (rangeSmall > maxRangeCount || rangeMid > maxRangeCount || rangeBig > maxRangeCount) {
//                System.out.println("全是某一区间值：rangeSmall="+rangeSmall+"  rangeMid="+rangeMid+"  rangeBig="+rangeBig);
//                System.out.println("全是某一区间组合："+items);
//                System.out.println(curCounts.size());
                if (iterator.next() != null) {
                    iterator.remove();
                }
//                System.out.println(curCounts.size());
                isAll = true;
            }
        }


//        for (List<Integer> curCount : curCounts) {
//            //计数都是偶数或者奇数
//            int count = 0;
//            //全是大中小某一个区间值
//            int rangeSmall = 0;
//            int rangeMid = 0;
//            int rangeBig = 0;
//            for (Integer integer : curCount) {
//                if (integer%2==0) {
//                    count++;
//                }
//                if (integer<=11) {
//                    rangeSmall++;
//                }
//                if (integer>11 && integer<=22) {
//                    rangeMid++;
//                }
//                if (integer>22) {
//                    rangeBig++;
//                }
//            }
//            if (count==0 || count==6) {
//                System.out.println("全是期数或偶数的组合："+curCount);
//                System.out.println(curCounts.size());
//                curCounts.remove(curCount);
//                System.out.println(curCounts.size());
//                isAll=true;
//            }
//            if (rangeSmall==6 || rangeMid==6 || rangeBig==6) {
//                System.out.println("全是某一区间值：rangeSmall="+rangeSmall+"  rangeMid="+rangeMid+"  rangeBig="+rangeBig);
//                System.out.println("全是某一区间组合："+curCount);
//                System.out.println(curCounts.size());
////                curCounts.remove(curCount);
//                System.out.println(curCounts.size());
//                isAll=true;
//            }
//
//
//        }
        return isAll;
    }

    private static List<DoubleBallBean> genComsphere(int count) {
        List<DoubleBallBean> countList = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            Set<Integer> blueItemCom = new HashSet<>();
            for (int i = 0; i < 6; i++) {
                while (!blueItemCom.add((int) (Math.random() * 33 + 1))) {
                    if (blueItemCom.size() == 6) {
                        break;
                    }
                }
            }
            List<Integer> blueItemComArray = new ArrayList<Integer>(blueItemCom);
            Collections.sort(blueItemComArray);
            DoubleBallBean ballBean = new DoubleBallBean();

            //检查当前的篮球是否包含20-29的数，因为判定本期一定会有20+的球出现；
            boolean isFlag = checkBlueBalls(blueItemComArray);
            boolean isHadSeven = checkBlueBallsHadSeven(blueItemComArray);
            if (isFlag && !isHadSeven) {
                ballBean.blueBalls = blueItemComArray;
                ballBean.redBall = getRandom();
                System.out.println(ballBean);
                countList.add(ballBean);
            }
        }
        return countList;
    }

    private static boolean checkBlueBallsHadSeven(List<Integer> blueItemComArray) {
        for (Integer integer : blueItemComArray) {
            if (integer.equals(7)) {
                return true;
            }
        }
        return false;
    }

    private static int getRandom() {
        int curNo = (int) (Math.random() * 16 + 1);
        if (curNo==7){
            return getRandom();
        }
        return curNo;
    }

    ////检查当前的篮球是否包含20-29的数，因为判定本期一定会有20+的球出现；
    private static boolean checkBlueBalls(List<Integer> blueItemComArray) {
        Iterator iterator = blueItemComArray.iterator();
        boolean isFlag=false;
        while (iterator.hasNext()) {
            Integer curNo = (Integer) iterator.next();
            if (curNo>=20 && curNo<=29){
                isFlag = true;
            }
        }
        return isFlag;
    }

    public static List<String> txt2String(File file) {
//        StringBuilder result = new StringBuilder();
        List<String> ress = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                result.append(System.lineSeparator()+s);
                ress.add(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ress;
//        return result.toString();
    }

    public static void checkHadGoal(List<List<Integer>> hadGoal, List<DoubleBallBean> aNos) {
        System.out.println("开始前：" + aNos);
        Iterator iterator = aNos.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean curItem = (DoubleBallBean) iterator.next();
            for (List<Integer> integers : hadGoal) {
                int count = 0;
                for (Integer integer : curItem.blueBalls) {
                    for (Integer integer1 : integers) {
                        if (integer.equals(integer1)) {
                            count++;
                        }
                    }
                    if (count > 2) {
//                        System.out.println("准备remove："+integers);
//                        System.out.println("准备remove1："+curItem);
                        if (iterator.hasNext() && iterator.next() != null) {
                            iterator.remove();
                        }
                    }
                }

            }

        }
        System.out.println("完成后：" + aNos);
    }

    public static void checkLasGoal(List<List<Integer>> hadGoal, List<DoubleBallBean> aNos) {
        System.out.println("checkLasGoal开始前：" + aNos);
        Iterator iterator = aNos.iterator();
        while (iterator.hasNext()) {
            DoubleBallBean curItem = (DoubleBallBean) iterator.next();
            boolean shouldMove = checkItem(hadGoal, curItem);
            if (shouldMove) {
                iterator.remove();
            }
        }
        System.out.println("aNos.size=" + aNos.size());
        System.out.println("checkLasGoal完成后：" + aNos);
        for (DoubleBallBean aNo : aNos) {
            for (int i = 0; i < aNo.blueBalls.size(); i++) {
                if (i==aNo.blueBalls.size()-1){
                    System.out.print(aNo.blueBalls.get(i)+"");
                }else {
                    System.out.print(aNo.blueBalls.get(i)+" ");
                }
            }
            System.out.println("+"+aNo.redBall);
        }

    }

    private static boolean checkItem(List<List<Integer>> hadGoal, DoubleBallBean curItem) {
        boolean shouldMove = false;
        Set<List<Integer>> res = new HashSet<>();
        for (List<Integer> integers : hadGoal) {
            int count = 0;
            for (Integer integer : curItem.blueBalls) {
                for (Integer integer1 : integers) {
                    if (integer.equals(integer1)) {
                        count++;
                    }
                }
                if (count > 4) {
//                        System.out.println("准备remove："+integers);
//                        System.out.println("准备remove1："+curItem);
                    res.add(curItem.blueBalls);
                    shouldMove = true;
                    System.out.println("最里的循环");
                    break;
                }
            }
//            System.out.println("最外的循环");
        }
        return shouldMove;
    }
}
