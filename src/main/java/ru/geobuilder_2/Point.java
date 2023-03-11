package ru.geobuilder_2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class Point  {

    private transient IntegerProperty idPoint;
    private transient StringProperty namePoint;
    private transient StringProperty distancePoint;
    private transient StringProperty vAnglePoint;
    private transient StringProperty hAnglePoint;

    public Point(Integer idPoint, String namePoint, String distancePoint, String vAnglePoint, String hAnglePoint) {
        this.idPoint = new SimpleIntegerProperty(idPoint);
        this.namePoint = new SimpleStringProperty(namePoint);
        this.distancePoint = new SimpleStringProperty(distancePoint);
        this.vAnglePoint = new SimpleStringProperty(vAnglePoint);
        this.hAnglePoint = new SimpleStringProperty(hAnglePoint);

    }

    public final int getIdPoint() {
        return this.idPoint.get();
    }

    public final void setIdPoint(Integer idPoint) {
        this.idPoint.set(idPoint);
    }

    public final String getNamePoint() {
        return this.namePoint.get();
    }

    public final void setNamePoint(String namePoint) {
        this.namePoint.set(namePoint);
    }

    public final String getDistancePoint() {
        return this.distancePoint.get();
    }

    public final void setDistancePoint(String distancePoint) {
        this.distancePoint.set(distancePoint);
    }

    public final String getVAnglePoint() {
        return this.vAnglePoint.get();
    }

    public final void setVAnglePoint(String vAnglePoint) {
        this.vAnglePoint.set(vAnglePoint);
    }

    public final String getHAnglePoint() {
        return this.hAnglePoint.get();
    }

    public final void setHAnglePoint(String hAnglePoint) {
        this.hAnglePoint.set(hAnglePoint);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idPoint.get());
        hash = 61 * hash + Objects.hashCode(this.namePoint.get());
        hash = 61 * hash + Objects.hashCode(this.distancePoint.get());
        hash = 61 * hash + Objects.hashCode(this.vAnglePoint.get());
        hash = 61 * hash + Objects.hashCode(this.hAnglePoint.get());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Point other = (Point) obj;

        if (this.idPoint.get() != other.idPoint.get()) {
            return false;
        }
        if (this.namePoint.get() != other.namePoint.get()) {
            return false;
        }
        if (this.distancePoint.get() != other.distancePoint.get()) {
            return false;
        }
        if (this.vAnglePoint.get() != other.vAnglePoint.get()) {
            return false;
        }
        if (this.hAnglePoint.get() != other.hAnglePoint.get()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Point [idPoint=" + this.idPoint.get() + "\n" +  ", namePoint=" +
                this.namePoint.get() + "\n" + ", distancePoint=" +
                this.distancePoint.get() +  "\n" + ", vAnglePoint=" +
                this.vAnglePoint.get() + "\n" + ", hAnglePoint=" + this.hAnglePoint.get() + "]";
    }
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdPoint());
        s.writeUTF(getNamePoint());
        s.writeUTF(getDistancePoint());
        s.writeUTF(getVAnglePoint());
        s.writeUTF(getHAnglePoint());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idPoint = new SimpleIntegerProperty(s.readInt());
        namePoint = new SimpleStringProperty(s.readUTF());
        distancePoint = new SimpleStringProperty(s.readUTF());
        vAnglePoint = new SimpleStringProperty(s.readUTF());
        hAnglePoint = new SimpleStringProperty(s.readUTF());
    }
}

