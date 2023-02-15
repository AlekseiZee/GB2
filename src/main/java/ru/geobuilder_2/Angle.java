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


public class Angle implements Serializable {

    private transient IntegerProperty idAngle;
    private transient StringProperty vAngle;
    private transient StringProperty hAngle;

    public Angle(int idAngl, String vAngle, String hAngle) {
        this.idAngle = new SimpleIntegerProperty(idAngl);
        this.vAngle = new SimpleStringProperty(vAngle);
        this.hAngle = new SimpleStringProperty(hAngle);
    }

    public final int getIdAngle() {
        return this.idAngle.get();
    }

    public final String getVAngle() {
        return this.vAngle.get();
    }

    public final String getHAngle() {
        return this.hAngle.get();
    }

    public final void setIdAngle(Integer idAngle) {
        this.idAngle.set(idAngle);
    }

    public final void setVAngle(String vAngle) {
        this.vAngle.set(vAngle);
    }

    public final void setHAngle(String hAngle) {
        this.hAngle.set(hAngle);
    }

    public final IntegerProperty idAngleProperty() {
        return this.idAngle;
    }

    public final StringProperty vAngleProperty() {
        return this.vAngle;
    }

    public final StringProperty hAngleProperty() {
        return this.hAngle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Angle other = (Angle) obj;
        if (this.idAngle.get() != other.idAngle.get()) {
            return false;
        }
        if (this.vAngle.get() != other.vAngle.get()) {
            return false;
        }
        if (this.hAngle.get() != other.hAngle.get()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idAngle.get());
        hash = 61 * hash + Objects.hashCode(this.vAngle.get());
        hash = 61 * hash + Objects.hashCode(this.hAngle.get());
        return hash;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "idAngle=" + this.idAngle.get() +
                ", vAngle=" + this.vAngle.get() +
                ", hAngle=" + this.hAngle.get() +
                '}';
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdAngle());
        s.writeUTF(getVAngle());
        s.writeUTF(getHAngle());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idAngle = new SimpleIntegerProperty(s.readInt());
        vAngle = new SimpleStringProperty(s.readUTF());
        hAngle = new SimpleStringProperty(s.readUTF());
    }
}