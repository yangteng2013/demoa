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
        StringBuilder stringBuilder = new StringBuilder(",");
        for (int i = 0; i < blueBalls.size(); i++) {
            int cvalue = blueBalls.get(i);
            if (cvalue<=9) {
                stringBuilder.append(String.format("0%d",cvalue));
            }else {
                stringBuilder.append(cvalue);
            }
//            if (i!=blueBalls.size()-1) {
//            }
            stringBuilder.append(",");
        }
        stringBuilder.append(redBall);
        return stringBuilder.toString();


    }
}
