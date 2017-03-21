package com.snuux.gaui.ga;

import java.util.ArrayList;

/**
 * Created by 164-381 on 04.03.2017.
 */
public interface Chromosomal {
    void cross(Chromosomal ch, ArrayList<Integer> points);

    void mutate(int alghorithm);

    double getFFValue();

    ArrayList<Chromosomal> getChilds();

    ArrayList<Integer> getGens();

    void setGens(ArrayList<Integer> gensArray);

    String toString();
}
