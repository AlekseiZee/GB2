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


public class AngleJFX implements Serializable {
    private transient IntegerProperty idAngleJFX;
    private transient StringProperty vAngleJFX;
    private transient StringProperty hAngleJFX;
    private transient ru.geobuilder_2.persistence.entity.Angle angle;

    private transient ru.geobuilder_2.persistence.entity.Point point;

    public AngleJFX(int idAngleJFX, String vAngleJFX, String hAngleJFX) {
        this.idAngleJFX = new SimpleIntegerProperty(idAngleJFX);
        this.vAngleJFX = new SimpleStringProperty(vAngleJFX);
        this.hAngleJFX = new SimpleStringProperty(hAngleJFX);
    }

    public AngleJFX(ru.geobuilder_2.persistence.entity.Angle angle, ru.geobuilder_2.persistence.entity.Point point){
        this((int) angle.getId(), String.valueOf(angle.getVangle()), String.valueOf(angle.getHangle()));
        this.angle = angle;
        this.point = point;
    }

    public final int getIdAngleJFX() {
        return this.idAngleJFX.get();
    }

    public final String getVAngleJFX() {
        return this.vAngleJFX.get();
    }

    public final String getHAngleJFX() {
        return this.hAngleJFX.get();
    }

    public final void setIdAngleJFX(Integer idAngleJFX) {
        this.idAngleJFX.set(idAngleJFX);
    }

    public final void setVAngleJFX(String vAngle) {
        this.vAngleJFX.set(vAngle);
    }

    public final void setHAngleJFX(String hAngle) {
        this.hAngleJFX.set(hAngle);
    }

    public final IntegerProperty idAngleJFXProperty() {
        return this.idAngleJFX;
    }

    public final StringProperty vAngleJFXProperty() {
        return this.vAngleJFX;
    }

    public final StringProperty hAngleJFXProperty() {
        return this.hAngleJFX;
    }

    public ru.geobuilder_2.persistence.entity.Angle getAngle() {
        return angle;
    }

    public void setAngle(ru.geobuilder_2.persistence.entity.Angle angle) {
        this.angle = angle;
    }

    public ru.geobuilder_2.persistence.entity.Point getPoint() {
        return point;
    }

    public void setPoint(ru.geobuilder_2.persistence.entity.Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AngleJFX other = (AngleJFX) obj;
        if (this.idAngleJFX.get() != other.idAngleJFX.get()) {
            return false;
        }
        if (this.vAngleJFX.get() != other.vAngleJFX.get()) {
            return false;
        }
        if (this.hAngleJFX.get() != other.hAngleJFX.get()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idAngleJFX.get());
        hash = 61 * hash + Objects.hashCode(this.vAngleJFX.get());
        hash = 61 * hash + Objects.hashCode(this.hAngleJFX.get());
        return hash;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "idAngle=" + this.idAngleJFX.get() +
                ", vAngle=" + this.vAngleJFX.get() +
                ", hAngle=" + this.hAngleJFX.get() +
                '}';
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdAngleJFX());
        s.writeUTF(getVAngleJFX());
        s.writeUTF(getHAngleJFX());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idAngleJFX = new SimpleIntegerProperty(s.readInt());
        vAngleJFX = new SimpleStringProperty(s.readUTF());
        hAngleJFX = new SimpleStringProperty(s.readUTF());
    }
}