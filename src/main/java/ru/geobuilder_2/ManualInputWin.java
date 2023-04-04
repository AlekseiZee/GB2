package ru.geobuilder_2;

import java.io.Serializable;
import java.util.ArrayList;

public class ManualInputWin implements Serializable {

    private transient ArrayList<PointJFX> pointsManualInputWin = new ArrayList<PointJFX> ();
    private transient ArrayList<AngleJFX> anglesManualInputWin = new ArrayList<AngleJFX>();

    public ManualInputWin(ArrayList<PointJFX> pointsManualInputWin, ArrayList<AngleJFX> anglesManualInputWin){
        this.pointsManualInputWin = new ArrayList<>(pointsManualInputWin);
        this.anglesManualInputWin = new ArrayList<>(anglesManualInputWin);
    }

}
