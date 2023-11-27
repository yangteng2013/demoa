package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static java.lang.System.exit;

/**
 * https://kaijiang.78500.cn/ssq/
 * <p>
 * 历史比较器：
 * https://www.78500.cn/tool/ssqdb.html
 */
public class DBall {
    static List<List<Integer>> hadAl = new ArrayList<List<Integer>>();
    private static final int SAMECOUNT = 6; //2组红球进行比较，相同个数>=4个时，就打印出来

    public static void main(String[] args) {

//        init();
//        init2();
//        init3();

        //需要生成多少组
        int countGroup = 1000;
        //需要生成10组双色球
        List<DoubleBallBean> getCountGroupBalls = generalDoubleBalls(countGroup);

        if (getCountGroupBalls!=null && getCountGroupBalls.size()>0) {
//            打印随机生成双色球
            for (int i = 0; i < getCountGroupBalls.size(); i++) {
                System.out.println("第"+i+"次随机生成的双色球："+ getCountGroupBalls.get(i).toString());
            }

        }

        exit(0);

    }

    /**
     * 生成{@param countGroup}组双色球
     * @param countGroup 多少组
     * @return
     */
    private static List<DoubleBallBean> generalDoubleBalls(int countGroup) {
        List<DoubleBallBean> doubleBallBeanList = new ArrayList<>();

        //默认随机生成一组
        if (countGroup<=0) {
            countGroup = 1;
        }

        for (int i = 0; i < countGroup; i++) {
            DoubleBallBean doubleBallBean = genDoubleBall();
            if (doubleBallBean.redBalls!=null) {
                doubleBallBeanList.add(doubleBallBean);
            }
        }

        return doubleBallBeanList;
    }

    /**
     * 随机生成一组双色球
     * @return
     */
    private static DoubleBallBean genDoubleBall() {
        Random random = new Random();
        DoubleBallBean ballBean = new DoubleBallBean();
        // 生成红球
        int[] redBalls = new int[6];
        for (int i = 0; i < redBalls.length; i++) {
            redBalls[i] = random.nextInt(33) + 1;
            for (int j = 0; j < i; j++) {
                if (redBalls[i] == redBalls[j]) {
                    i--;
                    break;
                }
            }
        }
        // 生成蓝球
        int blueBall = random.nextInt(16) + 1;

//        // 输出结果
//        System.out.println("红球：" + redBalls[0] + " " + redBalls[1] + " " + redBalls[2] + " " + redBalls[3] + " " + redBalls[4] + " " + redBalls[5]);
//        System.out.println("蓝球：" + blueBall);
        Arrays.sort(redBalls);


        //随机的一组红色求跟历史中奖记录进行匹配是否存在超过指定的个数，
        checkGoaled(redBalls);

        //检查是否全部是基数
        boolean allOdd = checkArrOdd(redBalls);
        if (allOdd) {
            //所有都是奇数，则不添加
            return ballBean;
        }
        //检查是否全部是偶数
        boolean allEven = checkArrEven(redBalls);
        if (allEven) {
            //所有都是偶数，则不添加
            return ballBean;
        }
        ballBean.redBalls = new ArrayList<>();
        for (int redBall : redBalls) {
            ballBean.redBalls.add(redBall);
        }

        ballBean.blueBall = blueBall;
        return ballBean;
    }

    private static boolean checkArrOdd(int[] redBalls) {
        boolean allOdd = true;
        for (int redBall : redBalls) {
            if (redBall%2==0) {
                allOdd = false;
                break;
            }
        }
        return allOdd;
    }
    private static boolean checkArrEven(int[] redBalls) {
        boolean allEven = true;
        for (int redBall : redBalls) {
            if (redBall%2!=0) {
                allEven = false;
                break;
            }
        }
        return allEven;
    }

    private static boolean checkGoaled(int[] redBalls) {
        List<Integer> sameRedBalls = new ArrayList<>();
        //获取已经中间的排列组合
        Iterator<Set<Integer>> iterator= getHadDballs2().iterator();
        int countEven=0;
        int countOdd=0;

        while (iterator.hasNext()) {
            Set<Integer> dball = iterator.next();
            List<Integer> blueItemComArray = new ArrayList<Integer>(dball);
            Collections.sort(blueItemComArray);
            int[] arr2 = blueItemComArray.stream().mapToInt(Integer::intValue).toArray();


//            boolean allEven = checkArrEven(arr2);
//            if (allEven) {
//                countEven++;
//                System.out.println("下面的中奖号码全是偶数");
//                for (int i : arr2) {
//                    System.out.print(i+",");
//                }
//                System.out.println("上面的中奖号码全是偶数");
//            }
//            boolean allOdd = checkArrOdd(arr2);
//            if (allOdd) {
//                countOdd++;
//                System.out.println("下面的中奖号码全是奇数");
//                for (int i : arr2) {
//                    System.out.print(i+",");
//                }
//                System.out.println("上面的中奖号码全是奇数");
//            }

            List<Integer> commoneElements = check2GroupsArrays(redBalls, arr2,SAMECOUNT);
            if (commoneElements.size()>=0) {
                sameRedBalls.addAll(commoneElements);
            }
        }
//        System.out.println(String.format("总共有%d个全是偶数的，%d个全是奇数的",countEven,countOdd));
        return sameRedBalls.size()>0;
    }


    private static List<Integer> check2GroupsArrays(int[] arr1, int[] arr2, int sameCount){

        List<Integer> dests = new ArrayList<>();

        Set<Integer>set1 = new HashSet<>();
        Set<Integer>commonElements = new HashSet<>();
        for (int i : arr1) {
            set1.add(i);
        }
        for (int i : arr2) {
            if (set1.contains(i)){
                commonElements.add(i);
            }
        }
        if (commonElements.size()>=sameCount) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i : arr1) {
                stringBuilder.append(String.valueOf(i)).append("、");
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            for (int i2 : arr2) {
                stringBuilder2.append(String.valueOf(i2)).append("、");
            }
            System.out.println(String.format("存在%s个相同元素的组-》arr1:"+stringBuilder.toString()+"  arr2:"+stringBuilder2, sameCount));
            for (Integer commonElement : commonElements) {
                dests.add(commonElement);
            }
            return dests;
        }
        return new ArrayList<Integer>();
    }


    private static void init3() {

        getHadDballs2();

//        int winningsNoCount = compireWithWinngsNum(blueItemComArray);

    }

    private static void init2() {



//        List<String> res2 = getHadDballs();
//
//
////
        List<DoubleBallBean> curCounts = genComsphere(1000);
        for (int i = 0; i < curCounts.size(); i++) {
            DoubleBallBean doubleBallBean = curCounts.get(i);
            System.out.println(doubleBallBean.toString());
        }
        System.out.println("curCounts:"+curCounts.size());
//
//        Set<Integer>blueItemCom = new HashSet<>();
//        blueItemCom.add(new Integer(1));
//        blueItemCom.add(new Integer(3));
//        blueItemCom.add(new Integer(5));
//        blueItemCom.add(new Integer(7));
//        blueItemCom.add(new Integer(9));
//        blueItemCom.add(new Integer(11));
//
////        blueItemCom.add(new Integer(2));
////        blueItemCom.add(new Integer(4));
////        blueItemCom.add(new Integer(6));
////        blueItemCom.add(new Integer(8));
////        blueItemCom.add(new Integer(10));
////        blueItemCom.add(new Integer(12));
//
//        blueItemCom.add(new Integer(13));
//        blueItemCom.add(new Integer(4));
//        blueItemCom.add(new Integer(17));
//        blueItemCom.add(new Integer(18));
//        blueItemCom.add(new Integer(28));
//        blueItemCom.add(new Integer(29));
//        //是否全是奇数或者偶数
//        boolean isAllOddEvenNo = checkAllOddEvenNo(blueItemCom);
//        System.out.println(isAllOddEvenNo);

//        checkHadBallIsOddEven(getHadDballs2());

//        checkContinuousCombination(Collections.singletonList(blueItemCom));
//        checkContinuousCombination(getHadDballs2());


    }
    /**
     * 输出历史开奖记录中的 连续组合排列
     * @param res2
     */
    private static void checkContinuousCombination(List<Set<Integer>> res2) {
        Iterator<Set<Integer>> iterator= res2.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Set<Integer> dball = iterator.next();
            List<Integer> blueItemComArray = new ArrayList<Integer>(dball);
            Collections.sort(blueItemComArray);


            int countItem = checkContinuoCombination(blueItemComArray,1);
            if (countItem>0){
                count++;
                System.out.println(blueItemComArray + " "+countItem+"次连号");
            }
        }
        System.out.println("历史开奖记录总共"+res2.size()+"次中有"+count+"组连续号码的排列组合");
    }
    /**
     * 输出历史开奖记录中的 全部是奇数 或者偶数的组合
     * @param res2
     */
    private static void checkHadBallIsOddEven(List<Set<Integer>> res2) {
        Iterator<Set<Integer>> iterator= res2.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Set<Integer> dball = iterator.next();
            boolean isAllOddEven = checkAllOddEvenNo(dball);
            if (isAllOddEven) {
                List<Integer> blueItemComArray = new ArrayList<Integer>(dball);
                Collections.sort(blueItemComArray);
                count++;
                System.out.println(blueItemComArray);
            }
        }
        System.out.println("历史开奖记录总共"+res2.size()+"次中有"+count+"次全奇数偶数的排列组合");
    }

    /**
     * 获取开奖历史记录
     * @return
     */
    private static List<String> getHadDballs() {
        List<String> res = txt2String(new File("src/com/company/abc.txt"));
        List<String> res2 =new ArrayList<>();
        Iterator iterator = res.iterator();
        while (iterator.hasNext()) {
            String ssr = (String) iterator.next();
            String[] srss=ssr.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 8; i++) {
                sb.append(srss[i]);
                if (i<7){
                    sb.append(",");
                }
            }
            res2.add(sb.toString());
        }
        res.clear();
        return res2;
    }

    private static List<Set<Integer>> getHadDballs2() {
        List<String> res = txt2String(new File("src/com/company/abc.txt"));
        List<Set<Integer>> res2 =new ArrayList<>();
        Iterator iterator = res.iterator();
        while (iterator.hasNext()) {
            String ssr = (String) iterator.next();
            String[] srss=ssr.split(",");
            Set<Integer> dbs = new HashSet<>();
            for (int i = 1; i < 7; i++) {
                dbs.add(Integer.valueOf(srss[i]));
            }
            res2.add(dbs);
        }
        res.clear();
        return res2;
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
            for (Integer integer : cItem.redBalls) {
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
                boolean equals = compareWithItem(items.redBalls, integers);
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
            for (int i = items.redBalls.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    break;
                }
                int a = items.redBalls.get(i) - items.redBalls.get(i - 1);
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

            for (Integer item : items.redBalls) {
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
        int continuousFiveCount=0;
        int isAllOddEvenCount=0;
        int isRepeatCount=0;
        Set<Set<Integer>>blues = new HashSet<>();
        for (int j = 0; j < count; j++) {
            Set<Integer> blueItemCom = new HashSet<>();
            for (int i = 0; i < 6; i++) {
                while (!blueItemCom.add((int) (Math.random() * 33 + 1))) {
                    if (blueItemCom.size() == 6) {
                        break;
                    }
                }
            }
            //是否全是奇数或者偶数
            boolean isAllOddEvenNo = checkAllOddEvenNo(blueItemCom);
            if (isAllOddEvenNo) {
                isAllOddEvenCount++;
//                System.out.println("全是偶数/奇数:"+blueItemCom);
                continue;
            }

            List<Integer> blueItemComArray = new ArrayList<Integer>(blueItemCom);
            Collections.sort(blueItemComArray);

            int countItem = checkContinuoCombination(blueItemComArray,1);
            if (countItem>4){
//                System.out.println("连续5个以上的号："+blueItemComArray);
                continuousFiveCount++;
            }

            DoubleBallBean ballBean = new DoubleBallBean();
            if (!blues.add(blueItemCom)) {
//                System.out.println("重复添加blueItemCom:"+blueItemCom);
                isRepeatCount++;
                continue;
            }
            int winningsNoCount = compireWithWinngsNum(blueItemComArray);
            if (winningsNoCount<1) {
                continue;
            }
            ballBean.redBalls = blueItemComArray;
            ballBean.blueBall = getRandom();
            countList.add(ballBean);
//            //检查当前的篮球是否包含20-29的数，因为判定本期一定会有20+的球出现；
//            boolean isFlag = checkredBalls(blueItemComArray);
//            boolean isHadSeven = checkredBallsHadSeven(blueItemComArray);
//            if (isFlag && !isHadSeven) {
//                ballBean.redBalls = blueItemComArray;
//                ballBean.redBall = getRandom();
//                System.out.println(ballBean);
//                countList.add(ballBean);
//            }
        }

//        Set<Integer>curItem = new HashSet<>();
//        //02,03,07,12,20,31
//        String itesm = "01,11,12,14,16,25";
//        String[] ssr=itesm.split(",");
//        for (int i = 0; i < ssr.length; i++) {
//            curItem.add(Integer.valueOf(ssr[i]));
//        }
//        List<Integer> blueItemComArray = new ArrayList<Integer>(curItem);
//        Collections.sort(blueItemComArray);
//        int winningsNoCount = compireWithWinngsNum(blueItemComArray);
        int sizeDif = countList.size()-blues.size();
        System.out.println("奇数偶数："+isAllOddEvenCount+"  continuousFiveCount:"+continuousFiveCount+ " blues.size:" +blues.size()+" countList.size:" +countList.size() +" 重复次数："+ isRepeatCount);
        return countList;
    }

    private static int compireWithWinngsNum(List<Integer> originBlueItemArray) {
        //获取已经中间的排列组合
        Iterator<Set<Integer>> iterator= getHadDballs2().iterator();
        int count = 0;
        Set<Set<Integer>> mapped = new HashSet<>();
        while (iterator.hasNext()) {
            Set<Integer> dball = iterator.next();
            List<Integer> blueItemComArray = new ArrayList<Integer>(dball);
            Collections.sort(blueItemComArray);


            List<Integer> resultList = new ArrayList<>();
            for (Integer integer : originBlueItemArray) {
                if (blueItemComArray.contains(integer)) {
                    resultList.add(integer);
                }
            }

            int countItem = resultList.size();
            if (countItem>4){
                mapped.add(dball);
                count++;
                System.out.println(blueItemComArray + " "+originBlueItemArray+"存在相同号："+resultList);
            }
        }
        if (mapped.size()>0) {
//            System.out.println("mapped:"+mapped.size());
        }

        return mapped.size();
    }

    /**
     * //是否全是奇数或者偶数
     * @param blueItemCom
     * @return
     */
    private static boolean checkAllOddEvenNo(Set<Integer> blueItemCom) {
        int countOdd=0;
        for (Integer integer : blueItemCom) {
            int cur = integer.intValue();
            if (cur%2==0) {
                countOdd++;
            }else {
                countOdd--;
            }
        }
        return Math.abs(countOdd)==blueItemCom.size();
    }
    private static int checkContinuoCombination(List<Integer> blueItemCom,int stage) {
        int count=0;
        Collections.sort(blueItemCom);

        for (int i = 0; i < blueItemCom.size(); i++) {
            if (i< blueItemCom.size()-1) {
                if ((blueItemCom.get(i).intValue()+stage)==blueItemCom.get(i+1).intValue()) {
                    count++;
                }
            }
        }
        return count;
    }


    private static boolean checkredBallsHadSeven(List<Integer> blueItemComArray) {
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
    private static boolean checkredBalls(List<Integer> blueItemComArray) {
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
                for (Integer integer : curItem.redBalls) {
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
            for (int i = 0; i < aNo.redBalls.size(); i++) {
                if (i==aNo.redBalls.size()-1){
                    System.out.print(aNo.redBalls.get(i)+"");
                }else {
                    System.out.print(aNo.redBalls.get(i)+" ");
                }
            }
            System.out.println("+"+aNo.blueBall);
        }

    }

    private static boolean checkItem(List<List<Integer>> hadGoal, DoubleBallBean curItem) {
        boolean shouldMove = false;
        Set<List<Integer>> res = new HashSet<>();
        for (List<Integer> integers : hadGoal) {
            int count = 0;
            for (Integer integer : curItem.redBalls) {
                for (Integer integer1 : integers) {
                    if (integer.equals(integer1)) {
                        count++;
                    }
                }
                if (count > 4) {
//                        System.out.println("准备remove："+integers);
//                        System.out.println("准备remove1："+curItem);
                    res.add(curItem.redBalls);
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
