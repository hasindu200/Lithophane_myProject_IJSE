package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CurrentCalculator {


    public AnchorPane context;
    public TextField PrinterWattage;
    public TextField JobHours;
    public TextField lenthOfFilament;
    public Label disElectricity;
    public Label disFilamentCost;
    public Label disAmount;

    public void calculateOnAction(ActionEvent actionEvent) {

        double numUnit=0;
        double electricity=0;
        double filamentCost=0;
        double amountSpend=0;

        double printerWattage = Double.parseDouble(PrinterWattage.getText());
        double hours = Double.parseDouble(JobHours.getText());
        double lengthOfFilament = Double.parseDouble(lenthOfFilament.getText());


        numUnit=(printerWattage*hours)/1000;

        electricity=numUnit*22;

        disElectricity.setText(String.valueOf(electricity));


        filamentCost = lengthOfFilament*3;

        disFilamentCost.setText(String.valueOf(filamentCost));


        amountSpend = electricity+filamentCost;

        disAmount.setText(String.valueOf(amountSpend));






    }
}
