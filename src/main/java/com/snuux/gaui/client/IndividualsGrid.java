package com.snuux.gaui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Text;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.*;
import com.snuux.gaui.ga.Chromosomal;

import java.util.ArrayList;

/**
 * Created by snuux on 3/13/2017.
 */
public class IndividualsGrid extends VerticalPanel {
    ArrayList<Chromosomal> arr;
    String height;

    FlexTable grid;
    ScrollPanel scrollPanel;
    HTML infoLabel;

    public IndividualsGrid(ArrayList<Chromosomal> arr, String height) {
        this.arr = arr;
        this.height = height;
        scrollPanel = new ScrollPanel();
        infoLabel = new HTML();

        scrollPanel.setAlwaysShowScrollBars(true);
        scrollPanel.setHeight("20em");

        grid = new FlexTable();

        scrollPanel.add(grid);
        this.add(infoLabel);
        this.add(scrollPanel);
    }

    public IndividualsGrid() {
        this.arr = null;
        scrollPanel = new ScrollPanel();
        infoLabel = new HTML();

        scrollPanel.setAlwaysShowScrollBars(true);
        scrollPanel.setHeight("20em");

        grid = new FlexTable();

        scrollPanel.add(grid);
        this.add(infoLabel);
        this.add(scrollPanel);
    }

    public void setArr(ArrayList<Chromosomal> arr) {
        this.arr = arr;

        grid.removeAllRows();

        int individualSize = arr.get(0).getGens().size();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < individualSize + 1; j++) {
                if (j == individualSize){
                    Label l = new Label();
                    l.setText(NumberFormat.getFormat("0.00").format(arr.get(i).getFFValue())); //( String.format("%3.2f", arr.get(i).getFFValue()) );
                    grid.setWidget(i, arr.get(i).getGens().size(), l);

                    continue;
                }

                if (grid.getRowCount() > i && grid.getCellCount(i) > j) {
                    //оптимизация!!!
                    ((TextBox) grid.getWidget(i, j)).setValue(Integer.toString(arr.get(i).getGens().get(j)));
                } else {
                    TextBox tb = new TextBox();
                    tb.setValue(Integer.toString(arr.get(i).getGens().get(j)));
                    tb.setSize("1em", "1em");
                    grid.setWidget(i, j, tb);
                }
            }

            //String lol = "";
            //for (int k = 0; k < arr.get(i).getGens().size(); k++)
            //    lol += arr.get(i).getGens().get(k);
            //GWT.log(lol);

        }

        double avg = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        int minIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            double ffValue = arr.get(i).getFFValue();
            avg += ffValue;
            if (max < ffValue)
                max = ffValue;
            if (min > ffValue) {
                min = ffValue;
                minIndex = i;
            }
        }

        avg /= arr.size();

        infoLabel.setHTML("Min: " + NumberFormat.getFormat("0.00").format(min) + " Avg: " +
                NumberFormat.getFormat("0.00").format(avg) + " Max: " + NumberFormat.getFormat("0.00").format(max)
                + "<br> Кол-во: " + arr.size());
    }

    public FlexTable getGrid() {
        return grid;
    }
}
