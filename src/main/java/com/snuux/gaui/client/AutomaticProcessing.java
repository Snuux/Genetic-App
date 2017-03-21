package com.snuux.gaui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.Chromosomal;
import com.snuux.gaui.ga.GAAdditions;
import com.snuux.gaui.ga.GeneticEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by snuux on 3/12/2017.
 */
public class AutomaticProcessing extends VerticalPanel {
    GAUI gaui;

    VerticalPanel panel;
    FlexTable grid;
    TextBox cyclesCount;
    Button processButton;
    Button processRestartButton;


    IndividualsGrid individualsGrid;

    public AutomaticProcessing(GAUI gaui) {
        this.gaui = gaui;

        panel = new VerticalPanel();
        grid = new FlexTable();

        processButton = new Button("Генерировать далее");
        processRestartButton = new Button("Сбросить");
        cyclesCount = new TextBox();
        individualsGrid = new IndividualsGrid();

        cyclesCount.setValue("1");

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Автоматическое вычисление</b>"));

        grid = new FlexTable();

        grid.setWidget(0, 0, new Label("Выполнить сразу:"));
        grid.setWidget(0, 1, cyclesCount);
        grid.setWidget(0, 2, new Label("цикл(ов)."));
        grid.setWidget(0, 4, processButton);
        grid.setWidget(1, 0, new Label("Текущая генерация: " + gaui.getGeneticEngine().getCurGeneration() + ". "));
        grid.setWidget(1, 1, processRestartButton);

        processButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                for (int i = 0; i < Integer.parseInt(cyclesCount.getValue()) - 1; i++) {
                    gaui.getGeneticEngine().processOneCycle();
                }

                ArrayList<Chromosomal> arr = gaui.getGeneticEngine().processOneCycle();

                //for (int i = 0; i < arr.size(); i++) {
                    //individualsGrid.getGrid().setWidget(i, arr.get(i).getGens().size(), new Label(Double.toString(arr.get(i).getFFValue())));
                //}

                individualsGrid.setArr(arr);

                ((Label) grid.getWidget(1, 0)).setText("Текущая генерация: " + gaui.getGeneticEngine().getCurGeneration() + ". ");
            }
        });

        processRestartButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                initGrid();
            }
        });

        gaui.getGeneticEngine().setIndividualsList(gaui.firstGeneration.getFirstGeneration());
        initGrid();

        add(grid);

        FlexTable flexTable = new FlexTable();
        flexTable.setWidget(0,0,individualsGrid);
        add(flexTable);
    }

    void initGrid() {
        gaui.getGeneticEngine().setCurGeneration(0);
        gaui.getGeneticEngine().setIndividualsList(gaui.firstGeneration.getFirstGeneration());

        individualsGrid.setArr(gaui.getGeneticEngine().getIndividualsList());

        ((Label) grid.getWidget(1, 0)).setText("Текущая генерация: " + gaui.getGeneticEngine().getCurGeneration() + ". ");
    }
}
