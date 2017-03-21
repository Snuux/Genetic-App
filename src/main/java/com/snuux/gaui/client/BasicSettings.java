package com.snuux.gaui.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.*;

public class BasicSettings {
    GAUI gaui;
    private TextBox individualSize;
    private TextBox generationsCount;
    private TextBox generationSize;
    private TextBox beginPoint;
    private TextBox endPoint;
    VerticalPanel panel;
    FlexTable grid;

    public BasicSettings(GAUI gaui) {
        this.gaui = gaui;

        individualSize = new TextBox();
        generationsCount = new TextBox();
        generationSize = new TextBox();
        beginPoint = new TextBox();
        endPoint = new TextBox();

        individualSize.setValue("10");
        generationsCount.setValue("150");
        generationSize.setValue("50");
        beginPoint.setValue("0");
        endPoint.setValue("9");

        individualSize.setFocus(true);
        individualSize.selectAll();

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Базовые настройки</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Размер особи:"));
        grid.setWidget(0,1, individualSize);
        grid.setWidget(1,0, new Label("Количество циклов:"));
        grid.setWidget(1,1, generationsCount);
        grid.setWidget(2,0, new Label("Размер популяции:"));
        grid.setWidget(2,1, generationSize);
        grid.setWidget(3,0, new Label("Начальная точка:"));
        grid.setWidget(3,1, beginPoint);
        grid.setWidget(4,0, new Label("Конечная точка:"));
        grid.setWidget(4,1, endPoint);

        panel.add(grid);
        gaui.getSettingsTabPanel().add(panel);

        //this.gaui.getTabLayoutPanel()

        //RootPanel.get("individualSize").add(individualSize);
        //RootPanel.get("generationsCount").add(generationsCount);
        //RootPanel.get("generationSize").add(generationSize);
        //RootPanel.get("beginPoint").add(beginPoint);
        //RootPanel.get("endPoint").add(endPoint);
    }

    public int getIndividualSize() {
        return Integer.parseInt(individualSize.getValue());
    }

    public int getGenerationsCount() {
        return Integer.parseInt(generationsCount.getValue());
    }

    public int getGenerationSize() {
        return Integer.parseInt(generationSize.getValue());
    }

    public int getBeginPoint() {
        return Integer.parseInt(beginPoint.getValue());
    }

    public int getEndPoint() {
        return Integer.parseInt(endPoint.getValue());
    }
}
