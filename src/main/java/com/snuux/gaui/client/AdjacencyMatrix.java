package com.snuux.gaui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.GAAdditions;

import java.util.ArrayList;

public class AdjacencyMatrix {
    private TextBox multiplier;
    private TextBox breaksCount;
    private TextBox jointsCount;
    private Button genAdjaMatrixButton;
    private FlexTable adjaMatrixGrid;

    GAUI gaui;

    VerticalPanel panel;
    FlexTable grid;

    ArrayList< ArrayList<Double> > adjacencyMatrix;

    public AdjacencyMatrix(GAUI gaui) {
        this.gaui = gaui;

        multiplier = new TextBox();
        breaksCount = new TextBox();
        jointsCount = new TextBox();
        genAdjaMatrixButton = new Button("Сгенерировать");

        multiplier.setValue("1.0");
        breaksCount.setValue("3");
        jointsCount.setValue("3");

        genAdjaMatrixButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                genAdjaMatrix();
            }
        });

        //RootPanel.get("multiplier").add(multiplier);
        //RootPanel.get("breaksCount").add(breaksCount);
        //RootPanel.get("jointsCount").add(jointsCount);
        //RootPanel.get("genAdjaMatrix").add(genAdjaMatrixButton);


        adjaMatrixGrid = new FlexTable();
        int numRows = gaui.getBasicSettings().getIndividualSize();
        int numColumns = gaui.getBasicSettings().getIndividualSize();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                TextBox tb = new TextBox();
                tb.setValue("0");
                tb.setSize("2em","1em");
                adjaMatrixGrid.setWidget(row, col, tb);
            }
        }
        //RootPanel.get("adjaMatrixGrid").add(adjaMatrixGrid);


        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Настройки матрицы смежности</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Множитель:"));
        grid.setWidget(0,1, multiplier);
        grid.setWidget(1,0, new Label("Количество тупиков:"));
        grid.setWidget(1,1, breaksCount);
        grid.setWidget(2,0, new Label("Количество общих точек:"));
        grid.setWidget(2,1, jointsCount);

        panel.add(grid);
        panel.add(genAdjaMatrixButton);
        panel.add(adjaMatrixGrid);

        gaui.getAdjaMatrixSettingsTabPanel().add(panel);
        //gaui.getSettingsTabPanel().add(panel);

        genAdjaMatrix();
    }

    private void genAdjaMatrix() {
        int size = gaui.getBasicSettings().getIndividualSize();
        adjacencyMatrix = GAAdditions.generateAdjacencyMatrix(
                size, Double.parseDouble(multiplier.getValue()));
        GAAdditions.generateAdjacencyMatrixBreaks(adjacencyMatrix, Integer.parseInt(breaksCount.getValue()));
        GAAdditions.generateAdjacencyMatrixJoints(adjacencyMatrix, Integer.parseInt(jointsCount.getValue()));

        adjaMatrixGrid.removeAllRows();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                TextBox tb = new TextBox();
                tb.setValue(Double.toString(adjacencyMatrix.get(row).get(col)));
                tb.setSize("2em","1em");
                adjaMatrixGrid.setWidget(row, col, tb);
            }
        }
    }

    public ArrayList<ArrayList<Double>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
