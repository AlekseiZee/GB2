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

public class PointHeightJFX implements Serializable {

    private transient IntegerProperty idPointHeight;
    private transient StringProperty namePointHeight;
    private transient StringProperty distancePointHeight;

    public PointHeightJFX(Integer idPointHeight, String namePointHeight, String distancePointHeight) {
        this.idPointHeight = new SimpleIntegerProperty(idPointHeight);
        this.namePointHeight = new SimpleStringProperty(namePointHeight);
        this.distancePointHeight = new SimpleStringProperty(distancePointHeight);
    }


    public final int getIdPointHeight() {
        return this.idPointHeight.get();
    }

    public final void setIdPointHeight(Integer idPointHeight) {
        this.idPointHeight.set(idPointHeight);
    }

    public final String getNamePointHeight() {
        return this.namePointHeight.get();
    }

    public final void setNamePointHeight(String namePointHeight) {
        this.namePointHeight.set(namePointHeight);
    }

    public final String getDistancePointHeight() {
        return this.distancePointHeight.get();
    }

    public final void setDistancePointHeight(String distancePointHeight) {
        this.distancePointHeight.set(distancePointHeight);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idPointHeight.get());
        hash = 61 * hash + Objects.hashCode(this.namePointHeight.get());
        hash = 61 * hash + Objects.hashCode(this.distancePointHeight.get());
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
        final PointHeightJFX other = (PointHeightJFX) obj;

        if (this.idPointHeight.get() != other.idPointHeight.get()) {
            return false;
        }
        if (this.namePointHeight.get() != other.namePointHeight.get()) {
            return false;
        }
        if (this.distancePointHeight.get() != other.distancePointHeight.get()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Point [idPoint=" + this.idPointHeight.get() + "\n" +  ", namePoint=" +
                this.namePointHeight.get() + "\n" + ", distancePoint=" +
                this.distancePointHeight.get() + "]";
    }
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdPointHeight());
        s.writeUTF(getNamePointHeight());
        s.writeUTF(getDistancePointHeight());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idPointHeight = new SimpleIntegerProperty(s.readInt());
        namePointHeight = new SimpleStringProperty(s.readUTF());
        distancePointHeight = new SimpleStringProperty(s.readUTF());
    }
}

