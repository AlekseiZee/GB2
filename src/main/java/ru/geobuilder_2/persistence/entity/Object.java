package ru.geobuilder_2.persistence.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the object database table.
 * 
 */
@Entity
@NamedQuery(name="Object.findAll", query="SELECT o FROM Object o")
public class Object implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String number;

	private String operator;

	private String address;

	//bi-directional many-to-one association to Point
	@OneToMany(mappedBy="object", cascade = CascadeType.PERSIST)
	private List<Instance> instances = new ArrayList<>();

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public Instance addInstance(Instance instance) {
		getInstances().add(instance);
		instance.setObject(this);

		return instance;
	}

	public Object() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}