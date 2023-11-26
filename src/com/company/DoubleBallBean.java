package com.company;

import java.util.ArrayList;
import java.util.List;

public class DoubleBallBean {

    public List<Integer> redBalls;
    public int blueBall;

    @Override
    public String toString() {

//        return blueBalls + " " + redBall;
//        return "DoubleBallBean{" +
//                "blueBalls=" + blueBalls +
//                ", redBall=" + redBall +
//                '}';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < redBalls.size(); i++) {
            int cvalue = redBalls.get(i);
            if (cvalue<=9) {
                stringBuilder.append(String.format("0%d",cvalue));
            }else {
                stringBuilder.append(cvalue);
            }
//            if (i!=blueBalls.size()-1) {
//            }
            stringBuilder.append(",");
        }
        if (blueBall<10) {
            stringBuilder.append(String.format("0%d",blueBall));
        }else {
            stringBuilder.append(blueBall);
        }
        return stringBuilder.toString();


    }
}
