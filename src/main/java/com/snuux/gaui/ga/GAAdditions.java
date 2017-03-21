/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snuux.gaui.ga;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author snuux
 */
public class GAAdditions {
    public static ArrayList<Chromosomal> arrayListToSimpleChromoList(ArrayList<ArrayList<Integer>> chromosomeIntegerList, ArrayList<ArrayList<Double>> adjMatrix) {
        ArrayList<Chromosomal> s = new ArrayList();
        for (int i = 0; i < chromosomeIntegerList.size(); i++) {
            SimpleChrormosome chrom = new SimpleChrormosome(adjMatrix);
            chrom.setGens(chromosomeIntegerList.get(i));

            s.add(chrom);
        }
        
        return s;
    }
    
    public static ArrayList<Chromosomal> arrayListToTSPChromoList(ArrayList<ArrayList<Integer>> chromosomeIntegerList, ArrayList<ArrayList<Double>> adjMatrix) {
        ArrayList<Chromosomal> s = new ArrayList();
        for (int i = 0; i < chromosomeIntegerList.size(); i++) {
            TspChromosome chrom = new TspChromosome(adjMatrix);
            chrom.setGens(chromosomeIntegerList.get(i));

            s.add(chrom);
        }
        

        return s;
    }
    
    public static boolean distinctValues(ArrayList<Integer> arr){
        Set<Integer> foundNumbers = new HashSet<Integer>();
        for (int num : arr) {
            if(foundNumbers.contains(num)){
                return false;
            }
            foundNumbers.add(num);
        }              
        return true;          
    }

    public static void generateAdjacencyMatrixBreaks(ArrayList<ArrayList<Double>> a, int s1, int s2) {
        int n = a.size();
        int size;
        if (s1 != s2) {
            size = (int) (Math.random() * s2 + s1);
            size *= 2;
        } else
            size = s1 * 2;

        int r1, r2;
        for (int i = 0; i < size; i++) {
            r1 = (int) (Math.random() * n);//qrand() % ((n) - 0) + 0;
            r2 = (int) (Math.random() * n);

            if (r1 != r2) {
                a.get(r1).set(r2, 9999.0);
                a.get(r2).set(r1, 9999.0);
            }
        }
    }

    public static void generateAdjacencyMatrixJoints(ArrayList<ArrayList<Double>> a, int s1, int s2) {
        int n = a.size();
        int size;
        if (s1 != s2) {
            size = (int) (Math.random() * s2 + s1);
            size *= 2;
        } else
            size = s1 * 2;

        int r1, r2;
        for (int i = 0; i < size; i++) {
            r1 = (int) (Math.random() * n);//qrand() % ((n) - 0) + 0;
            r2 = (int) (Math.random() * n);

            if (r1 != r2) {
                a.get(r1).set(r2, 0.0);
                a.get(r2).set(r1, 0.0);
            }
        }
    }

    public static void generateAdjacencyMatrixJoints(ArrayList<ArrayList<Double>> a, int s1) {
        int n = a.size();

        for (int i = 0; i < s1; i++) {
            int r1 = (int) (Math.random() * n);
            int r2 = (int) (Math.random() * n);

            a.get(r1).set(r2, 0.0);
            a.get(r2).set(r1, 0.0);
        }
    }

    public static void generateAdjacencyMatrixBreaks(ArrayList<ArrayList<Double>> a, int s1) {
        int n = a.size();

        for (int i = 0; i < s1; i++) {
            int r1 = (int) (Math.random() * n);
            int r2 = (int) (Math.random() * n);

            a.get(r1).set(r2, 9999.0);
            a.get(r2).set(r1, 9999.0);
        }
    }

    public static ArrayList<ArrayList<Integer>> generateFirstGeneration(int individualSize, int generationSize, int s1, int s2) {
        ArrayList<ArrayList<Integer>> a = new ArrayList(generationSize);

        for (int i = 0; i < generationSize; i++) {
            a.add(new ArrayList(individualSize));
            for (int j = 0; j < individualSize; j++)
                a.get(i).add((int) (Integer.MIN_VALUE));

            a.get(i).set(0, s1);
            a.get(i).set(individualSize - 1, s2);

            for (int j = 1; j < individualSize - 1; j++)
                a.get(i).set(j, (int) (Math.random() * individualSize));
        }

        return a;
    }
    
    public static ArrayList<ArrayList<Integer>> generateFirstGenerationTSP(int generationSize, int generationsCount, int s1, int s2, double multiplayer) {
        ArrayList< ArrayList<Integer> > a = new ArrayList(generationsCount);

        int n = generationSize;
        for (int i = 0; i < generationsCount; i++)
        {
            a.add(new ArrayList(n));
            for (int j = 0; j < n; j++)
                a.get(i).add(-9999);

            a.get(i).set(0, s1);
            a.get(i).set(n-1, s2);

            int counter = 1;
            while(counter < n-1)
            {
                int r1 = (int) (Math.random() * n);
                boolean alreadyExist = false;
                for (int k = 0; k < n; k++)
                    if (r1 == a.get(i).get(k))
                        alreadyExist = true;

                if (!alreadyExist)
                {
                    a.get(i).set(counter, r1);
                    counter++;
                }
            }
        }
        
        
        for (int i = 0; i < a.size(); i++) {
            if (!distinctValues(a.get(i)))
            {
                System.out.print(i + " : ");
                for (int j = 0; j < a.get(i).size(); j++)
                    System.out.print(" " + a.get(i).get(j));
                System.out.println();
            }
                
        }

        return a;
    }

    public static ArrayList<ArrayList<Double>> generateAdjacencyMatrix(int n, double multiplayer) {
        ArrayList<ArrayList<Double>> a = new ArrayList(n);

        for (int i = 0; i < n; i++) {
            a.add(i, new ArrayList(n));
            for (int j = 0; j < n; j++) {
                if (i == j)
                    a.get(i).add(0.0);
                else {
                    a.get(i).add(Math.random() * multiplayer);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j)
                    a.get(i).set(j, a.get(j).get(i));
            }
        }

        return a;
    }
}
