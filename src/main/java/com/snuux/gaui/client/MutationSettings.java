package com.snuux.gaui.client;

import com.google.gwt.user.client.ui.*;

/**
 * Created by snuux on 3/12/2017.
 */
public class MutationSettings {
    FlexTable grid;
    VerticalPanel panel;
    GAUI gaui;

    TextBox mutationChance;
    TextBox mutationCount;
    ListBox mutationMode;


    public MutationSettings(GAUI gaui) {
        this.gaui = gaui;

        mutationChance = new TextBox();
        mutationCount = new TextBox();
        mutationMode = new ListBox();

        mutationChance.setValue("0.06");
        mutationCount.setValue("1");
        mutationMode.addItem("Замена на случайный", "neighbor");
        mutationMode.addItem("Замена на соседний", "random");

        panel = new VerticalPanel();
        panel.add(new HTML("<hr><b>Настройка мутации</b>"));

        grid = new FlexTable();

        grid.setWidget(0,0, new Label("Вероятность мутации:"));
        grid.setWidget(0,1, mutationChance);
        grid.setWidget(1,0, new Label("Кол-во возможных мутаций у одной особи:"));
        grid.setWidget(1,1, mutationCount);
        grid.setWidget(2,0, new Label("Способ мутации:"));
        grid.setWidget(2,1, mutationMode);

        panel.add(grid);
        gaui.getSettingsTabPanel().add(panel);

        //RootPanel.get("mutationChance").add(mutationChance);
        //RootPanel.get("mutationCount").add(mutationCount);
        //RootPanel.get("mutationMode").add(mutationMode);
    }

    public TextBox getMutationChance() {
        return mutationChance;
    }

    public TextBox getMutationCount() {
        return mutationCount;
    }

    public ListBox getMutationMode() {
        return mutationMode;
    }
}
