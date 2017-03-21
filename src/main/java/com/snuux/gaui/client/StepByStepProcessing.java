package com.snuux.gaui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.GeneticEngine;

import java.util.ArrayList;

/**
 * Created by snuux on 3/12/2017.
 */
public class StepByStepProcessing extends VerticalPanel {
    GAUI gaui;

    FlexTable buttonsGrid;
    Button selection;
    Button crossing;
    Button mutation;
    Button reduction;

    FlexTable individualsGrids;
    IndividualsGrid selectionGrid;
    IndividualsGrid crossingGrid;
    IndividualsGrid mutationGrid;
    IndividualsGrid reductionGrid;

    enum Operation {selection, crossing, mutation, reduction}

    Operation currentOperation;

    ArrayList<Integer> counters; //selection, crossing, mutation, reduction

    public StepByStepProcessing(GAUI gaui) {
        this.gaui = gaui;
        currentOperation = Operation.selection;

        counters = new ArrayList();
        counters.add(0);
        counters.add(0);
        counters.add(0);
        counters.add(0);

        initGeneticEngine();

        initGrids();
        initButtons();

        add(buttonsGrid);
        add(individualsGrids);
    }

    void initGeneticEngine() {
        gaui.getGeneticEngine().setCurGeneration(0);
        gaui.getGeneticEngine().setIndividualsList(gaui.firstGeneration.getFirstGeneration());
    }

    void initGrids() {
        individualsGrids = new FlexTable();
        selectionGrid = new IndividualsGrid();
        crossingGrid = new IndividualsGrid();
        mutationGrid = new IndividualsGrid();
        reductionGrid = new IndividualsGrid();

        individualsGrids.setWidget(0, 0, new Label("Селекция:"));
        individualsGrids.setWidget(1, 0, selectionGrid);

        individualsGrids.setWidget(0, 1, new Label("Скрещивание:"));
        individualsGrids.setWidget(1, 1, crossingGrid);

        individualsGrids.setWidget(2, 0, new Label("Мутация:"));
        individualsGrids.setWidget(3, 0, mutationGrid);

        individualsGrids.setWidget(2, 1, new Label("Редукция:"));
        individualsGrids.setWidget(3, 1, reductionGrid);

        selectionGrid.setArr(gaui.getGeneticEngine().getIndividualsList());
    }

    void initButtons() {
        buttonsGrid = new FlexTable();

        selection = new Button("Селекция [0]");
        crossing = new Button("Скрещивание [0]");
        mutation = new Button("Мутация [0]");
        reduction = new Button("Редукция [0]");

        buttonsGrid.setWidget(0, 0, selection);
        buttonsGrid.setWidget(0, 1, crossing);
        buttonsGrid.setWidget(0, 2, mutation);
        buttonsGrid.setWidget(0, 3, reduction);

        selection.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                gaui.getGeneticEngine().selection();

                selectionGrid.setArr(gaui.getGeneticEngine().getIndividualsList());
                gaui.getGeneticEngine().setCurGeneration(gaui.getGeneticEngine().getCurGeneration() + 1);

                currentOperation = Operation.selection;

                counters.set(0, counters.get(0)+1);
                selection.setText("Селекция ["+counters.get(0)+"]");
            }
        });

        crossing.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                gaui.getGeneticEngine().crossing();

                crossingGrid.setArr(gaui.getGeneticEngine().getIndividualsList());
                gaui.getGeneticEngine().setCurGeneration(gaui.getGeneticEngine().getCurGeneration() + 1);

                currentOperation = Operation.crossing;

                counters.set(1, counters.get(1)+1);
                crossing.setText("Скрещивание ["+counters.get(1)+"]");
            }
        });

        mutation.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                gaui.getGeneticEngine().mutation();

                mutationGrid.setArr(gaui.getGeneticEngine().getIndividualsList());
                gaui.getGeneticEngine().setCurGeneration(gaui.getGeneticEngine().getCurGeneration() + 1);

                currentOperation = Operation.mutation;

                counters.set(2, counters.get(2)+1);
                mutation.setText("Мутация ["+counters.get(2)+"]");
            }
        });

        reduction.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                gaui.getGeneticEngine().reduction();

                reductionGrid.setArr(gaui.getGeneticEngine().getIndividualsList());
                gaui.getGeneticEngine().setCurGeneration(gaui.getGeneticEngine().getCurGeneration() + 1);

                currentOperation = Operation.reduction;

                counters.set(3, counters.get(3)+1);
                reduction.setText("Редукция ["+counters.get(3)+"]");
            }
        });
    }
}
