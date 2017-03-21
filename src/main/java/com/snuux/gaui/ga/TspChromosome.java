/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snuux.gaui.ga;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author snuux
 */
public class TspChromosome implements Chromosomal{

    ArrayList<ArrayList<Double>> aadjacencyMatrix;

    ArrayList<Integer> gens;
    ArrayList<Chromosomal> childs; //Хромосомы после размножения

    TspChromosome(ArrayList<ArrayList<Double>> aadjacencyMatrix) {
        gens = new ArrayList();
        childs = new ArrayList();
        this.aadjacencyMatrix = aadjacencyMatrix;
    }

    @Override
    public void cross(Chromosomal g, ArrayList<Integer> points) {
        TspChromosome ch1 = new TspChromosome(aadjacencyMatrix);
        TspChromosome ch2 = new TspChromosome(aadjacencyMatrix);

        ch1.gens = this.getGens();
        ch2.gens = g.getGens();

        int edge = (int) (1+Math.random() * (gens.size()-1-1));
        int index = edge;
	int i = edge;
	boolean flag = true;
	while (i != edge || flag)
	{
		flag = false;

		boolean alreadyExists = false;
		for (int j = 0; j < index; j++)
		{
                    if (Objects.equals(ch2.gens.get(i), ch1.gens.get(j)))
                            alreadyExists = true;
		}

		if (!alreadyExists)
		{
                    ch1.gens.set(index, ch2.gens.get(i));
                    index++;
		}

		i++;
		if (i > gens.size()-1) i = 0;
	}
        
        childs.add(ch1);
        
        ch1.gens = this.getGens();
        ch2.gens = g.getGens();
        
        index = edge;
	i = edge;
	flag = true;
        while (i != edge || flag)
	{
		flag = false;

		boolean alreadyExists = false;
		for (int j = 0; j < index; j++)
		{
                    if (Objects.equals(ch1.gens.get(i), ch2.gens.get(j)))
                            alreadyExists = true;
		}

		if (!alreadyExists)
		{
                    ch2.gens.set(index, ch1.gens.get(i));
                    index++;
		}

		i++;
		if (i > gens.size()-1) i = 0;
	}
        
        childs.add(ch2);
        //System.out.println(childs.get(0).toString());
        //System.out.println(childs.get(1).toString());
    }

    @Override
    public void mutate(int alghorithm) {
        int r1 = (int) (Math.random() * (gens.size() - 1) + 1);
        int r2 = (int) (Math.random() * (gens.size() - 1) + 1);

        //Collections.swap(gens, r1, r2);
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
