package ru.geobuilder_2.persistence.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the point database table.
 * 
 */
@Entity
@NamedQuery(name="Point.findAll", query="SELECT p FROM Point p")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String code;

	private double distance;

	private double vAngle;

	private double hAngle;

	//bi-directional many-to-one association to Anglepair
	@OneToMany(mappedBy="point", cascade = CascadeType.PERSIST)
	private List<Anglepair> anglepairs = new ArrayList<>();

	//bi-directional many-to-one association to Instance
	@ManyToOne
	@JoinColumn(name="Id_instance")
	private Instance instance;

	public Point(ru.geobuilder_2.Point jfxP) {
		this.code = jfxP.getNamePoint();
		this.distance = Double.parseDouble(jfxP.getDistancePoint());
		this.vAngle = Double.parseDouble(jfxP.getVAnglePoint());
		this.hAngle = Double.parseDouble(jfxP.getHAnglePoint());
	}

	public Point(){}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getHAngle() {
		return this.hAngle;
	}

	public void setHAngle(double hAngle) {
		this.hAngle = hAngle;
	}

	public double getVAngle() {
		return this.vAngle;
	}

	public void setVAngle(double vAngle) {
		this.vAngle = vAngle;
	}

	public List<Anglepair> getAnglepairs() {
		return this.anglepairs;
	}

	public void setAnglepairs(List<Anglepair> anglepairs) {
		this.anglepairs = anglepairs;
	}

	public Instance getInstance() {
		return this.instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Anglepair addAnglepair(Anglepair anglepair) {
		getAnglepairs().add(anglepair);
		anglepair.setPoint(this);

		return anglepair;
	}

	public Anglepair removeAnglepair(Anglepair anglepair) {
		getAnglepairs().remove(anglepair);
		anglepair.setPoint(null);

		return anglepair;
	}


	@Override
	public String toString() {
		return "Point [id=" + id + ", code=" + code + ", distance=" + distance + ", hangle=" + hAngle + ", vangle="
				+ vAngle + ", anglepairs=" + anglepairs + ", instance=Instance instance]";
	}
}