package com.snuux.gaui.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by snuux on 3/12/2017.
 */
public class ReductionSettings {
    FlexTable grid;
    VerticalPanel panel;
    GAUI gaui;

    private TextBox generationAlive;
    private TextBox eliteIndividualsPercent;

    public ReductionSettings(GAUI gaui) {
        this.gaui = gaui;

        generationAlive = new TextBox();
        eliteIndividualsPercent = new TextBox();
        generationAlive.setValue("50");
        eliteIndividualsPercent.setValue("10");

        //RootPanel.get("generationAlive").add(generationAlive);

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Настройка редукции</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Количество особей в популяции:"));
        grid.setWidget(0,1, generationAlive);
        grid.setWidget(1,0, new Label("Процент элитных особей:"));
        grid.setWidget(1,1, eliteIndividualsPercent);

        panel.add(grid);
        gaui.getSettingsTabPanel().add(panel);
    }

    public TextBox getEliteIndividualsPercent() {
        return eliteIndividualsPercent;
    }

    public TextBox getGenerationAlive() {
        return generationAlive;
    }
}
