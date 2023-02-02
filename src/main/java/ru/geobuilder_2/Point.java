package ru.geobuilder_2;

public class Point {

    private Integer idPoint;
    private String namePoint;
    private String distancePoint;
    private String vAnglePoint;
    private String hAnglePoint;

    public Point(Integer idPoint, String namePoint, String distancePoint, String vAnglePoint, String hAnglePoint) {
        this.setIdPoint(idPoint);
        this.setNamePoint(namePoint);
        this.setDistancePoint(distancePoint);
        this.setVAnglePoint(vAnglePoint);
        this.setHAnglePoint(hAnglePoint);
    }

    public Integer getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public String getNamePoint() {
        return namePoint;
    }

    public void setNamePoint(String namePoint) {
        this.namePoint = namePoint;
    }

    public String getDistancePoint() {
        return distancePoint;
    }

    public void setDistancePoint(String distancePoint) {
        this.distancePoint = distancePoint;
    }

    public String getVAnglePoint() {
        return vAnglePoint;
    }

    public void setVAnglePoint(String vAnglePoint) {
        this.vAnglePoint = vAnglePoint;
    }

    public String getHAnglePoint() {
        return hAnglePoint;
    }

    public void setHAnglePoint(String hAnglePoint) {
        this.hAnglePoint = hAnglePoint;
    }

    @Override
    public String toString() {
        return "Point{" +
                "idPoint=" + idPoint +
                ", namePoint='" + namePoint + '\'' +
                ", distancePoint='" + distancePoint + '\'' +
                ", vAnglePoint='" + vAnglePoint + '\'' +
                ", hAnglePoint='" + hAnglePoint + '\'' +
                '}';
    }
}

