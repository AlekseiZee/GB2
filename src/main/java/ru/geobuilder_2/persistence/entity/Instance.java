package ru.geobuilder_2.persistence.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


/**
 * The persistent class for the instance database table.
 * 
 */
@Entity
@NamedQuery(name="Instance.findAll", query="SELECT i FROM Instance i")
public class Instance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="Type_of_work")
	private String typeOfWork;

	@Column(name="Basis_of_works")
	private String numberBasisOfWork;

	private String author;

	@Column(name="Photo_date")
	private Timestamp photoDateColumn;

	@Column(name="creation_date")
	private Timestamp creationDate;


	//bi-directional many-to-one association to Point
	@OneToMany(mappedBy="instance", cascade = CascadeType.PERSIST)
	private List<Point> points = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="Id_object")
	private Object object;


	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Instance() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	public String getNumberBasisOfWork() {
		return numberBasisOfWork;
	}

	public void setNumberBasisOfWork(String numberBasisOfWork) {
		this.numberBasisOfWork = numberBasisOfWork;
	}

	public Timestamp getPhotoDateColumn() {
		return photoDateColumn;
	}

	public void setPhotoDateColumn(Timestamp photoDateColumn) {
		this.photoDateColumn = photoDateColumn;
	}

	public List<Point> getPoints() {
		return this.points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Point addPoint(Point point) {
		getPoints().add(point);
		point.setInstance(this);

		return point;
	}

	public Point removePoint(Point point) {
		getPoints().remove(point);
		point.setInstance(null);

		return point;
	}

	@Override
	public String toString() {
		return "Instance [id=" + id + ", author=" + author + ", creationDate=" + creationDate + ", points=" + points + "]";
	}
}