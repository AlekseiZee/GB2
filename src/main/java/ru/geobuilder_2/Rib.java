package ru.geobuilder_2;

public class Rib {

    private Integer tier;
    private static int count = 0;
    private String ribLength;

    public Rib(String ribLength) {
        count++;
        this.tier = count;
        this.ribLength = ribLength;
    }

    public static void setCount() {
        Rib.count = --count;
    }

    public Integer getTier() {
        return tier;
    }

    public String getRibLength() {
        return ribLength;
    }

    public void setRibLength(String ribLength) {
        this.ribLength = ribLength;
    }
}
