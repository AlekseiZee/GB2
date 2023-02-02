package ru.geobuilder_2;

public class Rib {

    private Integer tier;
    private String ribLength;

    public Rib(Integer tier, String ribLength) {
        this.tier = tier;
        this.ribLength = ribLength;
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
