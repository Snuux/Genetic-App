package com.snuux.gaui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.Chromosomal;
import com.snuux.gaui.ga.GAAdditions;

import java.util.ArrayList;

/**
 * Created by snuux on 3/11/2017.
 */
public class FirstGeneration {
    ScrollPanel scrollPanel;
    TextBox firstGenerationSize;
    private Button genFirstGenerationButton;
    FlexTable firstGenerationGrid;

    ArrayList<Chromosomal> firstGeneration;

    VerticalPanel panel;
    FlexTable grid;

    GAUI gaui;

    public FirstGeneration(GAUI gaui) {
        this.gaui = gaui;
        firstGenerationSize = new TextBox();
        firstGenerationSize.setValue("150");

        //RootPanel.get("firstGenerationSize").add(firstGenerationSize);

        genFirstGenerationButton = new Button("Сгенерировать");

        genFirstGenerationButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                genFirstGeneration();
            }
        });

        genFirstGenerationButton.click();

        //RootPanel.get("genFirstGeneration").add(genFirstGenerationButton);
        //RootPanel.get("firstGenerationGrid").add(scrollPanel);

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Генерация первой популяции</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Размер первой популяции:"));
        grid.setWidget(0,1, firstGenerationSize);

        panel.add(grid);
        panel.add(genFirstGenerationButton);

        scrollPanel = new ScrollPanel();
        scrollPanel.setAlwaysShowScrollBars(true);
        scrollPanel.setHeight("20em");
        firstGenerationGrid = new FlexTable();
        scrollPanel.add(firstGenerationGrid);

        FlexTable flexTable = new FlexTable(); //чтобы scrollPanel прижимался
        flexTable.setWidget(0,0,scrollPanel);

        panel.add(flexTable);

        //gaui.getSettingsTabPanel().add(panel);
        //gaui.getTabLayoutPanel().add(panel, gaui.getTabLayoutPanel());
        gaui.getFirstGenerationTabPanel().add(panel);

        genFirstGeneration();
    }

    private void genFirstGeneration() {
        firstGenerationGrid.removeAllRows();

        ArrayList<ArrayList<Integer>> ar = GAAdditions.generateFirstGeneration(gaui.getBasicSettings().getIndividualSize(),
                Integer.parseInt(firstGenerationSize.getValue()), gaui.getBasicSettings().getBeginPoint(),
                gaui.getBasicSettings().getEndPoint());

        firstGeneration = GAAdditions.arrayListToSimpleChromoList(ar, gaui.getAdjacencyMatrix().getAdjacencyMatrix());

        for (int i = 0; i < ar.size(); i++)
            for (int j = 0; j < ar.get(i).size(); j++) {
                TextBox tb = new TextBox();
                tb.setValue(Double.toString(ar.get(i).get(j)));
                tb.setSize("1em","1em");
                firstGenerationGrid.setWidget(i, j, tb);
            }
    }

    public ArrayList<Chromosomal> getFirstGeneration() {
        return firstGeneration;
    }
}
