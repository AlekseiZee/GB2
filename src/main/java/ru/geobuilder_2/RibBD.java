package ru.geobuilder_2;

public class RibBD {

    private Integer tierBD;
    private static int countBD = 0;
    private String ribLengthBD;

    public RibBD(String ribLengthBD) {
        countBD++;
        this.tierBD = countBD;
        this.ribLengthBD = ribLengthBD;
    }

    public static void setCountBD() {
        RibBD.countBD = --countBD;
    }

    public Integer getTierBD() {
        return tierBD;
    }

    public String getRibLengthBD() {
        return ribLengthBD;
    }

    public void setRibLengthBD(String ribLengthBD) {
        this.ribLengthBD = ribLengthBD;
    }
}
