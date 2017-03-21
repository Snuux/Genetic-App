package com.snuux.gaui.ga;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 164-381 on 04.03.2017.
 */
public class GeneticEngine {
    ArrayList<Chromosomal> individualsList;

    int chromosomeLength;
    int curGeneration;
    int maxGeneration;

    boolean usePercentCap;
    int maxIndividualsCap;             //либо до макс. количества эл-тов
    double maxIndividualsPercentCap;   //либо до % от макс количества в популяции
    double firstBestIndividualsPercent;//сколько лучших особей точно не удалятся

    boolean useFixPoint;
    int fixPointValue;
    int pointsNumber;

    public enum CrossType {BestXBest, Random}

    public enum MutateType {Random, Neighbor}

    CrossType crosstype;
    MutateType mutateType;

    boolean useMutation;
    int mutateCount;
    double mutateChance;

    public GeneticEngine() {
        individualsList = new ArrayList();

        this.usePercentCap = false;
        this.useMutation = true;
        this.curGeneration = 0;
        this.maxGeneration = 150;
        this.maxIndividualsCap = 50;
        this.maxIndividualsPercentCap = 50;
        this.mutateChance = 0.06;
        this.firstBestIndividualsPercent = 10;
        this.crosstype = CrossType.BestXBest;
        this.useFixPoint = false;
        this.fixPointValue = 5;
        this.pointsNumber = 2;

        this.mutateType = MutateType.Random;
        this.mutateCount = 1;
    }

    public GeneticEngine(ArrayList<Chromosomal> individualsList) {
        setIndividualsList(individualsList);

        this.usePercentCap = false;
        this.useMutation = true;
        this.curGeneration = 0;
        this.maxGeneration = 150;
        this.maxIndividualsCap = 50;
        this.maxIndividualsPercentCap = 50;
        this.mutateChance = 0.06;
        this.firstBestIndividualsPercent = 10;
        this.crosstype = CrossType.BestXBest;
        this.useFixPoint = false;
        this.fixPointValue = 5;
        this.pointsNumber = 2;

        this.mutateType = MutateType.Random;
        this.mutateCount = 1;
    }

    public void processing() {
        while (curGeneration < maxGeneration) {
            selection();
            crossing();
            if (useMutation) {
                mutation();
            }
            reduction();

            printOneGenerationInfo();

            curGeneration++;
        }
    }

    public ArrayList<Chromosomal> processOneCycle() {
        selection();
        crossing();
        if (useMutation) {
            mutation();
        }
        reduction();

        //printOneGenerationInfo();

        curGeneration++;

        return individualsList;
    }

    public void selection() {
        if (crosstype == CrossType.BestXBest) {
            sortByFFValue();
        } else if (crosstype == CrossType.Random) {
            Collections.shuffle(individualsList);
        }
    }

    public void crossing() {
        //Complete todo points random/fix
        ArrayList<Integer> points = new ArrayList();
        if (useFixPoint) {
            points.add(fixPointValue);
        } else {
            if (pointsNumber == 0)
                return;

            for (int i = 0; i < pointsNumber; i++) {
                points.add((int) (Math.random() * (chromosomeLength - 1 - 1) + 1));
            }
        }

        //if (individualsList.size() <= 4)
        //    return;

        Collections.sort(points);

        for (int i = 0; i < individualsList.size() - 2; i += 2) {
            individualsList.get(i).cross(individualsList.get(i + 1), points);
        }

        //добавляем всех детей к родителям и удаляем
        for (int i = 0; i < individualsList.size(); i++) {
            individualsList.addAll(individualsList.get(i).getChilds());
            individualsList.get(i).getChilds().clear();
        }
    }

    public void mutation() {
        for (int j = 0; j < mutateCount; j++)
            for (int i = 0; i < individualsList.size(); i++) {
                double r1 = Math.random(); //0-0.999
                if (r1 < mutateChance) {
                    if (mutateType == MutateType.Random)
                        individualsList.get(i).mutate(0);
                    else if (mutateType == MutateType.Neighbor)
                        individualsList.get(i).mutate(1);
                }
            }
    }

    public void reduction() {
        if (crosstype == CrossType.BestXBest) {
            sortByFFValue();
        } else if (crosstype == CrossType.Random) {
            Collections.shuffle(individualsList);
        }

        if (maxIndividualsCap < individualsList.size() - 1) {
            individualsList.subList(maxIndividualsCap,
                    individualsList.size() - 1).clear();
        }
    }

    void printOneGenerationInfo() {
        System.out.print(curGeneration + ":");
        double avg = 0;
        double min = 999999;
        double max = -999999;
        int minIndex = 0;
        for (int i = 0; i < individualsList.size(); i++) {
            double ffValue = individualsList.get(i).getFFValue();
            avg += ffValue;
            if (max < ffValue)
                max = ffValue;
            if (min > ffValue) {
                min = ffValue;
                minIndex = i;
            }
        }
        avg /= individualsList.size();
        System.out.print("" + min + "   " + avg + "     " + max + "");
        //System.out.print(" Min: " + min + " Avg: " + avg + " Max: " + max + " ::: ");
        //System.out.print(individualsList.get(minIndex).toString());
        //System.out.println(" Count: " + individualsList.size());

    }

    void sortByFFValue() {
        for (int i = 0; i < individualsList.size(); ++i) {
            boolean swapped = false;
            for (int j = 0; j < individualsList.size() - (i + 1); ++j) {
                if (individualsList.get(j).getFFValue() > individualsList.get(j + 1).getFFValue()) {
                    Collections.swap(individualsList, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void setCurGeneration(int curGeneration) {
        this.curGeneration = curGeneration;
    }

    public ArrayList<Chromosomal> getIndividualsList() {
        return individualsList;
    }

    public void setIndividualsList(ArrayList<Chromosomal> individualsList) {
        this.individualsList.clear();
        this.individualsList.addAll(individualsList);
        setChromosomeLength(individualsList.get(0).getGens().size());
    }

    public MutateType getMutateType() {
        return mutateType;
    }

    public void setMutateType(MutateType mutateType) {
        this.mutateType = mutateType;
    }

    public int getMutateCount() {
        return mutateCount;
    }

    public void setMutateCount(int mutateCount) {
        this.mutateCount = mutateCount;
    }

    public int getCurGeneration() {
        return curGeneration;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public void setMaxGeneration(int maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public boolean isUsePercentCap() {
        return usePercentCap;
    }

    public void setUsePercentCap(boolean usePercentCap) {
        this.usePercentCap = usePercentCap;
    }

    public int getMaxIndividualsCap() {
        return maxIndividualsCap;
    }

    public void setMaxIndividualsCap(int maxIndividualsCap) {
        this.maxIndividualsCap = maxIndividualsCap;
    }

    public double getMaxIndividualsPercentCap() {
        return maxIndividualsPercentCap;
    }

    public void setMaxIndividualsPercentCap(double maxIndividualsPercentCap) {
        this.maxIndividualsPercentCap = maxIndividualsPercentCap;
    }

    public double getFirstBestIndividualsPercent() {
        return firstBestIndividualsPercent;
    }

    public void setFirstBestIndividualsPercent(double firstBestIndividualsPercent) {
        this.firstBestIndividualsPercent = firstBestIndividualsPercent;
    }

    public boolean isUseFixPoint() {
        return useFixPoint;
    }

    public void setUseFixPoint(boolean useFixPoint) {
        this.useFixPoint = useFixPoint;
    }

    public int getFixPointValue() {
        return fixPointValue;
    }

    public void setFixPointValue(int fixPointValue) {
        this.fixPointValue = fixPointValue;
    }

    public int getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(int pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    public CrossType getCrosstype() {
        return crosstype;
    }

    public void setCrosstype(CrossType crosstype) {
        this.crosstype = crosstype;
    }

    public boolean isUseMutation() {
        return useMutation;
    }

    public void setUseMutation(boolean useMutation) {
        this.useMutation = useMutation;
    }

    public double getMutateChance() {
        return mutateChance;
    }

    public void setMutateChance(double mutateChance) {
        this.mutateChance = mutateChance;
    }

    public int getChromosomeLength() {
        return chromosomeLength;
    }

    public void setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
    }
}
