package com.snuux.gaui.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by snuux on 3/12/2017.
 */
public class CrossingSettings {
    FlexTable grid;
    VerticalPanel panel;

    GAUI gaui;
    RadioButton fixPointCheck;
    RadioButton fixPointCountCheck;
    RadioButton randomPointCountCheck;
    TextBox fixPoint;
    TextBox fixPointCount;
    TextBox randomPointCount;


    public CrossingSettings(GAUI gaui) {
        this.gaui = gaui;

        VerticalPanel vPanel = new VerticalPanel();

        fixPointCheck = new RadioButton("radioGroup", "");
        fixPointCountCheck = new RadioButton("radioGroup", "");
        randomPointCountCheck = new RadioButton("radioGroup", "Использовать произвольное кол-во случайных точек");

        fixPointCheck.setChecked(true);

        //RootPanel.get("fixPointCheck").add(fixPointCheck);
        //RootPanel.get("fixPointCountCheck").add(fixPointCountCheck);
        //RootPanel.get("randomPointCountCheck").add(randomPointCountCheck);

        fixPoint = new TextBox();
        fixPointCount = new TextBox();

        fixPoint.setValue("5");
        fixPointCount.setValue("3");


        //RootPanel.get("fixPoint").add(fixPoint);
        //RootPanel.get("fixPointCount").add(fixPointCount);

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Настройка скрещивания</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, fixPointCheck);
        grid.setWidget(0,1, new Label("Использовать одну фикс. точку:"));
        grid.setWidget(0,2, fixPoint);
        grid.setWidget(1,0, fixPointCountCheck);
        grid.setWidget(1,1, new Label("Использовать n случ. точек:"));
        grid.setWidget(1,2, fixPointCount);
        panel.add(grid);

        panel.add(randomPointCountCheck);
        gaui.getSettingsTabPanel().add(panel);
    }

    public TextBox getFixPoint() {
        return fixPoint;
    }

    public TextBox getFixPointCount() {
        return fixPointCount;
    }

    public RadioButton getFixPointCheck() {
        return fixPointCheck;
    }

    public RadioButton getFixPointCountCheck() {
        return fixPointCountCheck;
    }

    public RadioButton getRandomPointCountCheck() {
        return randomPointCountCheck;
    }
}
