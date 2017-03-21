package com.snuux.gaui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.snuux.gaui.ga.GeneticEngine;

/**
 * Created by snuux on 3/13/2017.
 */
public class BasicSettingsSave extends VerticalPanel {
    GAUI gaui;

    Button button;

    public BasicSettingsSave(GAUI gaui) {
        this.gaui = gaui;

        button = new Button("Сохранить изменения");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                gaui.getGeneticEngine().setChromosomeLength(gaui.getBasicSettings().getIndividualSize());
                gaui.getGeneticEngine().setMaxGeneration(gaui.getBasicSettings().getGenerationsCount());
                gaui.getGeneticEngine().setMaxIndividualsCap(gaui.getBasicSettings().getGenerationSize());

                //Настройка селекции
                if (gaui.getSelectionSettings().getSelectionType() == "bestXbest")
                    gaui.getGeneticEngine().setCrosstype(GeneticEngine.CrossType.BestXBest);
                else
                    gaui.getGeneticEngine().setCrosstype(GeneticEngine.CrossType.Random);

                //Настройка кроссинга
                if (gaui.getCrossingSettings().getFixPointCheck().getValue()) {
                    gaui.getGeneticEngine().setUseFixPoint(true);
                    gaui.getGeneticEngine().setFixPointValue(Integer.parseInt(gaui.getCrossingSettings().getFixPoint().getValue()));
                } else {
                    gaui.getGeneticEngine().setUseFixPoint(false);
                }

                if (gaui.getCrossingSettings().getRandomPointCountCheck().getValue()) {
                    double max = gaui.getGeneticEngine().getChromosomeLength();
                    gaui.getGeneticEngine().setPointsNumber((int) (Math.random() * max));
                } else {
                    gaui.getGeneticEngine().setPointsNumber(Integer.parseInt(gaui.getCrossingSettings().getFixPointCount().getValue()));
                }

                //Настройка мутации
                gaui.getGeneticEngine().setMutateChance(Double.parseDouble(gaui.getMutationSettings().getMutationChance().getValue()));
                gaui.getGeneticEngine().setMutateCount(Integer.parseInt(gaui.getMutationSettings().getMutationCount().getValue()));

                if (gaui.getMutationSettings().getMutationMode().getValue(
                        gaui.getMutationSettings().getMutationMode().getSelectedIndex()) == "random")
                    gaui.getGeneticEngine().setMutateType(GeneticEngine.MutateType.Random);
                else
                    gaui.getGeneticEngine().setMutateType(GeneticEngine.MutateType.Neighbor);

                //Настройка редукции
                gaui.getGeneticEngine().setMaxIndividualsCap(Integer.parseInt(gaui.getReductionSettings().getGenerationAlive().getValue()));
                gaui.getGeneticEngine().setFirstBestIndividualsPercent(Integer.parseInt(gaui.getReductionSettings().getEliteIndividualsPercent().getValue()));
            }
        });

        add(button);
        gaui.getSettingsTabPanel().add(this);
    }
}
