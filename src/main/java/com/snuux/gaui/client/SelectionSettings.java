package com.snuux.gaui.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by snuux on 3/12/2017.
 */
public class SelectionSettings {
    FlexTable grid;
    VerticalPanel panel;
    GAUI gaui;

    ListBox selectionTypeDropBox;

    public SelectionSettings(GAUI gaui) {
        this.gaui = gaui;

        selectionTypeDropBox = new ListBox(false);
        selectionTypeDropBox.addItem("Лучшие с лучшими", "bestXbest");
        selectionTypeDropBox.addItem("Случайно","random");

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Настройка селекции</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Тип селекции:"));
        grid.setWidget(0,1, selectionTypeDropBox);

        panel.add(grid);
        gaui.getSettingsTabPanel().add(panel);

        //RootPanel.get("selectionType").add(selectionTypeDropBox);
    }

    String getSelectionType() {
        return selectionTypeDropBox.getValue(selectionTypeDropBox.getSelectedIndex());
    }
}
