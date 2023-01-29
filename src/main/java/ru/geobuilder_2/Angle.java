package ru.geobuilder_2;

public class Angle {

    private Integer idAngle;
    private String vAngle;
    private String hAngle;
    private static int countAngle = 0;

    public static int getCountPoint() {
        return getCountAngle();
    }

    public Angle(String vAngle, String hAngle) {
        countAngle++;
        this.setIdAngle(countAngle);
        this.setVAngle(vAngle);
        this.setHAngle(hAngle);
    }

    public static void setCountAngle() {
        Angle.countAngle = --countAngle;
    }

    public static int getCountAngle() {
        return countAngle;
    }

    public static void setCountAngle(int countAngle) {
        Angle.countAngle = countAngle;
    }

    public Integer getIdAngle() {
        return idAngle;
    }

    public void setIdAngle(Integer idAngle) {
        this.idAngle = idAngle;
    }

    public String getVAngle() {
        return vAngle;
    }

    public void setVAngle(String vAngle) {
        this.vAngle = vAngle;
    }

    public String getHAngle() {
        return hAngle;
    }

    public void setHAngle(String hAngle) {
        this.hAngle = hAngle;
    }
}