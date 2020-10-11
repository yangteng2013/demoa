package com.company;

import java.util.ArrayList;
import java.util.List;

public class DoubleBallBean {

    public List<Integer> blueBalls;
    public int redBall;

    @Override
    public String toString() {

//        return blueBalls + " " + redBall;
//        return "DoubleBallBean{" +
//                "blueBalls=" + blueBalls +
//                ", redBall=" + redBall +
//                '}';
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> dbal = new ArrayList<>();
        for (Integer blueBall : blueBalls) {
            dbal.add(blueBall);
            stringBuilder.append(blueBall).append(" ");
        }
        dbal.add(redBall);
        stringBuilder.append(redBall);
        /**
         *  6 7 14 18 19 33  +10
         *  1 9 15 17 23 32  +9
         *  6 10 13 21 22 25 + 4
         *  7 12 15 23 27 31 + 11
         *  3 13 17 20 26 33 + 2
         *  1 5 17 23 26 32  +11
         *  3 13 18 19 29 33 + 11
         *  7 17 18 25 26 30 + 8
         *  2 7 14 16 19 21  +4
         *  5 9 18 23 24 30  +5
         *  16 17 19 20 26 32 + 2
         *  3 6 11 24 25 31  +2
         *
         *
         * 06 08 11 22 25 33
         * 02 08 21 25 26 30
         * 01 20 23 26 27 32
         * 03 09 16 17 20 26
         * 10 15 17 27 29 31
         * 04 05 10 13 15 19
         * 01 06 12 18 22 24
         * 01 09 11 12 16 19
         * 02 21 23 26 31 32
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */


        return stringBuilder.toString();


    }
}
