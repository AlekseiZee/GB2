package ru.geobuilder_2;

import java.io.Serializable;
import java.util.ArrayList;

public class ManualInputWin implements Serializable {

    private transient ArrayList<Point> pointsManualInputWin = new ArrayList<Point> ();
    private transient ArrayList<Angle> anglesManualInputWin = new ArrayList<Angle>();

    public ManualInputWin(ArrayList<Point> pointsManualInputWin, ArrayList<Angle> anglesManualInputWin){
        this.pointsManualInputWin = new ArrayList<>(pointsManualInputWin);
        this.anglesManualInputWin = new ArrayList<>(anglesManualInputWin);
    }

}
