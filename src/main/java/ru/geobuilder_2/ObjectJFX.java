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

public class ObjectJFX implements Serializable {

    private transient IntegerProperty idObjectJFX;
    private transient StringProperty numberObjectJFX;
    private transient StringProperty operatorObjectJFX;
    private transient StringProperty addressObjectJFX;
    private transient ru.geobuilder_2.persistence.entity.Object object;

    public ObjectJFX(Integer idObjectJFX, String numberObjectJFX, String operatorObjectJFX, String addressObjectJFX) {
        this.idObjectJFX = new SimpleIntegerProperty(idObjectJFX);
        this.numberObjectJFX = new SimpleStringProperty(numberObjectJFX);
        this.operatorObjectJFX = new SimpleStringProperty(operatorObjectJFX);
        this.addressObjectJFX = new SimpleStringProperty(addressObjectJFX);
    }

    public ObjectJFX (ru.geobuilder_2.persistence.entity.Object obj){
        this((int) obj.getId(), obj.getNumber(), obj.getOperator(), obj.getAddress());
        this.object = obj;
    }

    public final int getIdObjectJFX() {
        return this.idObjectJFX.get();
    }

    public final void setIdObjectJFX(Integer idObjectJFX) {
        this.idObjectJFX.set(idObjectJFX);
    }

    public final String getNumberObjectJFX() {
        return this.numberObjectJFX.get();
    }

    public final void setNumberObjectJFX(String numberObjectJFX) {
        this.numberObjectJFX.set(numberObjectJFX);
    }

    public final String getOperatorObjectJFX() {
        return this.operatorObjectJFX.get();
    }

    public final void setOperatorObjectJFX(String operatorObjectJFX) {
        this.operatorObjectJFX.set(operatorObjectJFX);
    }

    public final String getAddressObjectJFX() {
        return this.addressObjectJFX.get();
    }

    public final void setAddressObjectJFX(String addressObjectJFX) {
        this.addressObjectJFX.set(addressObjectJFX);
    }

    public ru.geobuilder_2.persistence.entity.Object getObject() {
        return object;
    }

    public void setObject(ru.geobuilder_2.persistence.entity.Object object) {
        this.object = object;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idObjectJFX.get());
        hash = 61 * hash + Objects.hashCode(this.numberObjectJFX.get());
        hash = 61 * hash + Objects.hashCode(this.operatorObjectJFX.get());
        hash = 61 * hash + Objects.hashCode(this.addressObjectJFX.get());
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
        final ObjectJFX other = (ObjectJFX) obj;

        if (this.idObjectJFX.get() != other.idObjectJFX.get()) {
            return false;
        }
        if (this.numberObjectJFX.get() != other.numberObjectJFX.get()) {
            return false;
        }
        if (this.operatorObjectJFX.get() != other.operatorObjectJFX.get()) {
            return false;
        }
        if (this.addressObjectJFX.get() != other.addressObjectJFX.get()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Point [idObjectJFX=" + this.idObjectJFX.get() + "\n" +  ", numberObjectJFX=" +
                this.numberObjectJFX.get() + "\n" + ", operatorObjectJFX=" +
                this.operatorObjectJFX.get() +  "\n" + ", addressObjectJFX=" +
                this.addressObjectJFX.get() + "]";
    }
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdObjectJFX());
        s.writeUTF(getNumberObjectJFX());
        s.writeUTF(getOperatorObjectJFX());
        s.writeUTF(getAddressObjectJFX());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idObjectJFX = new SimpleIntegerProperty(s.readInt());
        numberObjectJFX = new SimpleStringProperty(s.readUTF());
        operatorObjectJFX = new SimpleStringProperty(s.readUTF());
        addressObjectJFX = new SimpleStringProperty(s.readUTF());
    }
}
