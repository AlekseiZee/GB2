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
 * The persistent class for the anglepair database table.
 *
 */
@Entity
@NamedQuery(name="ListRibs.findAll", query="SELECT rs FROM Ribs rs")
public class ListRibs implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    //bi-directional many-to-one association to Angle
    @OneToMany(mappedBy="listRibs", cascade = CascadeType.PERSIST)
    private List<Rib> rib = new ArrayList<>();

    //bi-directional many-to-one association to Point
    @ManyToOne
    @JoinColumn(name="Id_Object")
    private Object object;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Rib> getRib() {
        return rib;
    }

    public void setRib(List<Rib> rib) {
        this.rib = rib;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}