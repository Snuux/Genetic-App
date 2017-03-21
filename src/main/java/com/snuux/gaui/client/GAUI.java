package com.snuux.gaui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.GeneticEngine;

public class GAUI implements EntryPoint {
    BasicSettings basicSettings;
    AdjacencyMatrix adjacencyMatrix;
    FirstGeneration firstGeneration;
    SelectionSettings selectionSettings;
    CrossingSettings crossingSettings;
    MutationSettings mutationSettings;
    ReductionSettings reductionSettings;
    AutomaticProcessing automaticProcessing;
    StepByStepProcessing stepByStepProcessing;
    BasicSettingsSave basicSettingsSave;

    Button startComputing;
    DecoratedTabPanel tabLayoutPanel;
    VerticalPanel settingsTabPanel;
    VerticalPanel firstGenerationTabPanel;
    VerticalPanel adjaMatrixSettingsTabPanel;
    VerticalPanel autoProcessingTabPanel;
    VerticalPanel stepByStepTabPanel;

    GeneticEngine geneticEngine;

    public void onModuleLoad() {
        geneticEngine = new GeneticEngine();

        settingsTabPanel = new VerticalPanel();
        firstGenerationTabPanel = new VerticalPanel();
        adjaMatrixSettingsTabPanel = new VerticalPanel();
        autoProcessingTabPanel = new VerticalPanel();
        stepByStepTabPanel = new VerticalPanel();

        tabLayoutPanel = new DecoratedTabPanel();//2.5, Style.Unit.EM);
        tabLayoutPanel.setHeight("120em");
        //tabLayoutPanel.add(new Label("sdsd"), "tab1");

        basicSettings = new BasicSettings(this);
        adjacencyMatrix = new AdjacencyMatrix(this);
        firstGeneration = new FirstGeneration(this);
        selectionSettings = new SelectionSettings(this);
        crossingSettings = new CrossingSettings(this);
        mutationSettings = new MutationSettings(this);
        reductionSettings = new ReductionSettings(this);
        automaticProcessing = new AutomaticProcessing(this);
        stepByStepProcessing = new StepByStepProcessing(this);
        basicSettingsSave = new BasicSettingsSave(this);

        tabLayoutPanel.add(settingsTabPanel, "1 Начальная настройка");
        tabLayoutPanel.add(firstGenerationTabPanel, "2 Генерация первой популяции");
        tabLayoutPanel.add(adjaMatrixSettingsTabPanel, "3 Настрока матрицы смежности");
        tabLayoutPanel.add(automaticProcessing, "4 Автоматическое вычисление");
        tabLayoutPanel.add(stepByStepProcessing, "5 Пошаговое вычисление");

        RootPanel.get("tabLayoutPanel").add(tabLayoutPanel);
        tabLayoutPanel.selectTab(0);

        setUpStartComputingButton();
    }

    public SelectionSettings getSelectionSettings() {
        return selectionSettings;
    }

    public CrossingSettings getCrossingSettings() {
        return crossingSettings;
    }

    public MutationSettings getMutationSettings() {
        return mutationSettings;
    }

    public ReductionSettings getReductionSettings() {
        return reductionSettings;
    }

    public GeneticEngine getGeneticEngine() {
        return geneticEngine;
    }

    public AutomaticProcessing getAutomaticProcessing() {
        return automaticProcessing;
    }

    private void setUpStartComputingButton() {
        startComputing = new Button();

    }

    public VerticalPanel getFirstGenerationTabPanel() {
        return firstGenerationTabPanel;
    }

    public VerticalPanel getAdjaMatrixSettingsTabPanel() {
        return adjaMatrixSettingsTabPanel;
    }

    public VerticalPanel getAutoProcessingTabPanel() {
        return autoProcessingTabPanel;
    }

    public VerticalPanel getStepByStepTabPanel() {
        return stepByStepTabPanel;
    }

    public VerticalPanel getSettingsTabPanel() {
        return settingsTabPanel;
    }

    public BasicSettings getBasicSettings() {
        return basicSettings;
    }

    public AdjacencyMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public DecoratedTabPanel getTabLayoutPanel() {
        return tabLayoutPanel;
    }
}
