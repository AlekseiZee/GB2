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

public class InstanceJFX implements Serializable {
    private transient IntegerProperty idInstanceJFX;
    private transient StringProperty typeOfWorkInstanceJFX;
    private transient StringProperty numberBasisOfWorkInstanceJFX;
    private transient StringProperty authorInstanceJFX;
    private transient StringProperty photoDateColumnInstanceJFX;
    private transient StringProperty creationDateInstanceJFX;

    public InstanceJFX(Integer idInstanceJFX, String typeOfWorkInsanceJFX, String numberBasisOfWorkInstanceJFX,
                       String authorInstanceJFX, String photoDateColumnInstanceJFX, String creationDateInstanceJFX) {
        this.idInstanceJFX = new SimpleIntegerProperty(idInstanceJFX);
        this.typeOfWorkInstanceJFX = new SimpleStringProperty(typeOfWorkInsanceJFX);
        this.numberBasisOfWorkInstanceJFX = new SimpleStringProperty(numberBasisOfWorkInstanceJFX);
        this.authorInstanceJFX = new SimpleStringProperty(authorInstanceJFX);
        this.photoDateColumnInstanceJFX = new SimpleStringProperty(photoDateColumnInstanceJFX);
        this.creationDateInstanceJFX = new SimpleStringProperty(creationDateInstanceJFX);
    }

    public final int getIdInstanceJFX() {
        return this.idInstanceJFX.get();
    }

    public final void setIdInstanceJFX(Integer idInstanceJFX) {
        this.idInstanceJFX.set(idInstanceJFX);
    }

    public final String getTypeOfWorkInstanceJFX() {
        return this.typeOfWorkInstanceJFX.get();
    }

    public final void setTypeOfWorkInstanceJFX(String typeOfWorkInstanceJFX) {
        this.typeOfWorkInstanceJFX.set(typeOfWorkInstanceJFX);
    }

    public final String getNumberBasisOfWorkInstanceJFX() {
        return this.numberBasisOfWorkInstanceJFX.get();
    }

    public final void setNumberBasisOfWorkInstanceJFX(String numberBasisOfWorkInstanceJFX) {
        this.numberBasisOfWorkInstanceJFX.set(numberBasisOfWorkInstanceJFX);
    }

    public final String getAuthorInstanceJFX() {
        return this.authorInstanceJFX.get();
    }

    public final void setAuthorInstanceJFX(String authorInstanceJFX) {
        this.authorInstanceJFX.set(authorInstanceJFX);
    }

    public final String getPhotoDateColumnInstanceJFX() {
        return this.photoDateColumnInstanceJFX.get();
    }

    public final void setPhotoDateColumnInstanceJFX(String photoDateColumnInstanceJFX) {
        this.photoDateColumnInstanceJFX.set(photoDateColumnInstanceJFX);
    }

    public final String getCreationDateInstanceJFX() {
        return this.creationDateInstanceJFX.get();
    }

    public final void setCreationDateInstanceJFX(String creationDateInstanceJFX) {
        this.creationDateInstanceJFX.set(creationDateInstanceJFX);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idInstanceJFX.get());
        hash = 61 * hash + Objects.hashCode(this.typeOfWorkInstanceJFX.get());
        hash = 61 * hash + Objects.hashCode(this.numberBasisOfWorkInstanceJFX.get());
        hash = 61 * hash + Objects.hashCode(this.authorInstanceJFX.get());
        hash = 61 * hash + Objects.hashCode(this.photoDateColumnInstanceJFX.get());
        hash = 61 * hash + Objects.hashCode(this.creationDateInstanceJFX.get());
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
        final InstanceJFX other = (InstanceJFX) obj;

        if (this.idInstanceJFX.get() != other.idInstanceJFX.get()) {
            return false;
        }
        if (this.typeOfWorkInstanceJFX.get() != other.typeOfWorkInstanceJFX.get()) {
            return false;
        }
        if (this.numberBasisOfWorkInstanceJFX.get() != other.numberBasisOfWorkInstanceJFX.get()) {
            return false;
        }
        if (this.authorInstanceJFX.get() != other.authorInstanceJFX.get()) {
            return false;
        }
        if (this.photoDateColumnInstanceJFX.get() != other.photoDateColumnInstanceJFX.get()) {
            return false;
        }
        if (this.creationDateInstanceJFX.get() != other.creationDateInstanceJFX.get()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Point [idInstanceJFX=" + this.idInstanceJFX.get() + "\n" + ", typeOfWorkInstanceJFX=" +
                this.typeOfWorkInstanceJFX + "\n" + ", numberBasisOfWorkInstanceJFX=" +
                this.numberBasisOfWorkInstanceJFX.get() + "\n" + ", authorInstanceJFX=" +
                this.authorInstanceJFX.get() +  "\n" + ", photoDateColumnInstanceJFX=" +
                this.photoDateColumnInstanceJFX.get() + "\n" + ", creationDateInstanceJFX=" +
                this.creationDateInstanceJFX + "]";
    }

    private void writeInstance(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getIdInstanceJFX());
        s.writeUTF(getTypeOfWorkInstanceJFX());
        s.writeUTF(getNumberBasisOfWorkInstanceJFX());
        s.writeUTF(getAuthorInstanceJFX());
        s.writeUTF(getPhotoDateColumnInstanceJFX());
        s.writeUTF(getCreationDateInstanceJFX());
    }

    private void readInstance(ObjectInputStream s) throws IOException, ClassNotFoundException {
        idInstanceJFX = new SimpleIntegerProperty(s.readInt());
        typeOfWorkInstanceJFX = new SimpleStringProperty(s.readUTF());
        numberBasisOfWorkInstanceJFX = new SimpleStringProperty(s.readUTF());
        authorInstanceJFX = new SimpleStringProperty(s.readUTF());
        photoDateColumnInstanceJFX = new SimpleStringProperty(s.readUTF());
        creationDateInstanceJFX = new SimpleStringProperty(s.readUTF());
    }
}
