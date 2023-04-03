package ru.geobuilder_2.persistence.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


/**
 * The persistent class for the anglepair database table.
 * 
 */
@Entity
@NamedQuery(name="Anglepair.findAll", query="SELECT a FROM Anglepair a")
public class Anglepair implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//bi-directional many-to-one association to Angle
	@OneToMany(mappedBy="anglepair", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Angle> angles = new ArrayList<>();

	//bi-directional many-to-one association to Point
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Id_Point")
	private Point point;


	public Anglepair() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Angle> getAngles() {
		return this.angles;
	}

	public void setAngles(List<Angle> angles) {
		this.angles = angles;
	}

	public Angle addAngle(Angle angle) {
		getAngles().add(angle);
		angle.setAnglepair(this);

		return angle;
	}

	public Angle removeAngle(Angle angle) {
		getAngles().remove(angle);
		angle.setAnglepair(null);

		return angle;
	}

	public Point getPoint() {
		return this.point;
	}

	public void setPoint(Point point1) {
		this.point = point1;
	}

	@Override
	public String toString() {
		return "Anglepair [id=" + id + ", angles=" + angles + ", point=Point point@"+this.point.hashCode()+"]";
	}
}