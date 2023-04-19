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


public class AngleHeightJFX implements Serializable {
    private transient IntegerProperty idAngleHeightJFX;
    private transient StringProperty heightObjectJFX;
    private transient StringProperty hAngleHeightJFX;

    public AngleHeightJFX(int idAngleHeightJFX, String heightObjectJFX, String hAngleHeightJFX) {
        this.idAngleHeightJFX = new SimpleIntegerProperty(idAngleHeightJFX);
        this.heightObjectJFX = new SimpleStringProperty(heightObjectJFX);
        this.hAngleHeightJFX = new SimpleStringProperty(hAngleHeightJFX);
    }

    public final int getIdAngleHeightJFX() {
        return this.idAngleHeightJFX.get();
    }

    public final String getHeightObjectJFX() {
        return this.heightObjectJFX.get();
    }

    public final String getHAngleJFX() {
        return this.hAngleHeightJFX.get();
    }

    public final void setIdAngleHeightJFX(Integer idAngleHeightJFX) {
        this.idAngleHeightJFX.set(idAngleHeightJFX);
    }

    public final void setHeightObjectJFX(String vAngle) {
        this.heightObjectJFX.set(vAngle);
    }

    public final void setHAngleJFX(String hAngle) {
        this.hAngleHeightJFX.set(hAngle);
    }

    public final IntegerProperty idAngleHeightJFXProperty() {
        return this.idAngleHeightJFX;
    }

    public final StringProperty heightObjectJFXProperty() {
        return this.heightObjectJFX;
    }

    public final StringProperty hAngleHeightJFXProperty() {
        return this.hAngleHeightJFX;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AngleHeightJFX other = (AngleHeightJFX) obj;
        if (this.idAngleHeightJFX.get() != other.idAngleHeightJFX.get()) {
            return false;
        }
        if (this.heightObjectJFX.get() != other.heightObjectJFX.get()) {
            return false;
        }
        if (this.hAngleHeightJFX.get() != other.hAngleHeightJFX.get()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idAngleHeightJFX.get());
        hash = 61 * hash + Objects.hashCode(this.heightObjectJFX.get());
        hash = 61 * hash + Objects.hashCode(this.hAngleHeightJFX.get());
        return hash;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "idAngle=" + this.idAngleHeightJFX.get() +
                ", vAngle=" + this.heightObjectJFX.get() +
                ", hAngle=" + this.hAngleHeightJFX.get() +
                ']';
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdAngleHeightJFX());
        s.writeUTF(getHeightObjectJFX());
        s.writeUTF(getHAngleJFX());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idAngleHeightJFX = new SimpleIntegerProperty(s.readInt());
        heightObjectJFX = new SimpleStringProperty(s.readUTF());
        hAngleHeightJFX = new SimpleStringProperty(s.readUTF());
    }
}