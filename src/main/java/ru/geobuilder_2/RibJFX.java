package ru.geobuilder_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * https://stackoverflow.com/questions/18791566/notserializableexception-on-simplelistproperty
 * https://gist.github.com/james-d/a7202039b00170256293
 * @author A0707220
 *
 */
public class RibJFX implements Serializable {

	// transient fields are excluded from serialization
    private transient IntegerProperty tier;
    private transient StringProperty ribLength;

    private transient ru.geobuilder_2.persistence.entity.Rib rib;

    public RibJFX(int tier, String ribLength) {
        this.tier = new SimpleIntegerProperty(tier);
        this.ribLength = new SimpleStringProperty(ribLength);
    }

    public RibJFX(ru.geobuilder_2.persistence.entity.Rib rib){
        this(rib.getTier(), String.valueOf(rib.getRibLength()));
        this.rib = rib;
    }

    public final int getTier() {
        return this.tier.get();
    }

    public final String getRibLength() {
        return this.ribLength.get();
    }

    public final void setRibLength(String ribLength) {
        this.ribLength.set(ribLength);
    }

    public final void setTier(Integer tier) {
        this.tier.set(tier);
    }

    public final IntegerProperty tierProperty() {
    	return this.tier;
    }

    public final StringProperty ribLengthProperty() {
    	return this.ribLength;
    }

    public ru.geobuilder_2.persistence.entity.Rib getRib() {
        return rib;
    }

    public void setRib(ru.geobuilder_2.persistence.entity.Rib rib) {
        this.rib = rib;
    }

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.tier.get());
		hash = 61 * hash + Objects.hashCode(this.ribLength.get());
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
		final RibJFX other = (RibJFX) obj;
        if (this.tier.get() != other.tier.get()) {
            return false;
        }
        if (this.ribLength.get() != other.ribLength.get()) {
            return false;
        }
        return true;
	}

    @Override
    public String toString() {
        return "RibJFX{" +
                "tier=" + tier +
                ", ribLength=" + ribLength +
                ", rib=" + rib +
                '}';
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getTier());
        s.writeUTF(getRibLength());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    	tier = new SimpleIntegerProperty(s.readInt());
        ribLength = new SimpleStringProperty(s.readUTF());
    }
}
