package com.snuux.gaui.ga;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 164-381 on 04.03.2017.
 */
public class SimpleChrormosome implements Chromosomal {
    ArrayList<ArrayList<Double>> aadjacencyMatrix;

    ArrayList<Integer> gens;
    ArrayList<Chromosomal> childs; //Хромосомы после размножения

    SimpleChrormosome(ArrayList<ArrayList<Double>> aadjacencyMatrix) {
        gens = new ArrayList();
        childs = new ArrayList();
        this.aadjacencyMatrix = aadjacencyMatrix;
    }

    @Override
    public void cross(Chromosomal g, ArrayList<Integer> points) {
        SimpleChrormosome ch1 = new SimpleChrormosome(aadjacencyMatrix);
        SimpleChrormosome ch2 = new SimpleChrormosome(aadjacencyMatrix);

        ch1.gens.add(gens.get(0));
        ch2.gens.add(gens.get(0));

        if (points.size() == 1) {
            int p = points.get(0);
            for (int i = 1; i < p; i++) {
                ch1.gens.add(gens.get(i));
                ch2.gens.add(g.getGens().get(i));
            }

            for (int i = p; i < gens.size() - 1; i++) {
                ch2.gens.add(gens.get(i));
                ch1.gens.add(g.getGens().get(i));
            }
        } else {
            int t1 = 1;
            int t2 = points.get(0);
            boolean isSwap = false;

            for (int j = t1; j < t2; j++) {
                ch1.gens.add(gens.get(j));
                ch2.gens.add(g.getGens().get(j));
            }

            for (int i = 1; i < points.size(); i++) {
                t1 = points.get(i - 1);
                t2 = points.get(i);

                for (int j = t1; j < t2; j++) {
                    if (isSwap) {
                        ch1.gens.add(gens.get(j));
                        ch2.gens.add(g.getGens().get(j));
                    } else {
                        ch2.gens.add(gens.get(j));
                        ch1.gens.add(g.getGens().get(j));
                    }
                }

                isSwap = !isSwap;
            }

            t1 = t2; //последние от points.get(послед.) до gens.size-2
            t2 = gens.size() - 1;

            for (int j = t1; j < t2; j++) {
                if (isSwap) {
                    ch1.gens.add(gens.get(j));
                    ch2.gens.add(g.getGens().get(j));
                } else {
                    ch2.gens.add(gens.get(j));
                    ch1.gens.add(g.getGens().get(j));
                }
            }
        }

        ch1.gens.add(gens.get(gens.size() - 1));
        ch2.gens.add(gens.get(gens.size() - 1));

        childs.add(ch1);
        childs.add(ch2);
    }

    @Override
    public void mutate(int alghorithm) {
        if (alghorithm == 0) {
            int r1 = (int) (Math.random() * (gens.size() - 1) + 1);
            int r2 = (int) (Math.random() * (gens.size()));

            gens.set(r1, r2);
            //Collections.swap(gens, r1, r2);
        } else if (alghorithm == 1) {
            int r1 = (int) (Math.random() * (gens.size() - 1-1-1) + 1+1);
            int r2;
            if (Math.random() > 0.5)
                r2 = 1;
            else
                r2 = -1;

            gens.set(r1, r1+r2);
            //Collections.swap(gens, r1, r1+r2);
        }


    }

    @Override
    public double getFFValue() {
        double path = 0;
        int comp1;
        int comp2;

        for (int i = 1; i < gens.size(); i++) {
            comp1 = gens.get(i - 1);
            comp2 = gens.get(i);

            path += aadjacencyMatrix.get(comp1).get(comp2);
        }

        return path;
    }

    @Override
    public ArrayList<Chromosomal> getChilds() {
        return childs;
    }

    @Override
    public ArrayList<Integer> getGens() {
        return gens;
    }

    @Override
    public void setGens(ArrayList<Integer> gensArray) {
        gens = gensArray;
    }

    @Override
    public String toString() {
        String a = "";
        for (int i = 0; i < gens.size(); i++)
            a += gens.get(i) + " ";
        return a;
    }

    public void setAadjacencyMatrix(ArrayList<ArrayList<Double>> aadjacencyMatrix) {
        this.aadjacencyMatrix = aadjacencyMatrix;
    }
}
