package ru.geobuilder_2.persistence.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


/**
 * The persistent class for the object database table.
 *
 */
@Entity
@NamedQuery(name="Rib.findAll", query="SELECT r FROM Rib r")
public class Rib implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Integer id;

    private Integer tier;

    public Rib() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getRibLength() {
        return ribLength;
    }

    public void setRibLength(Integer ribLength) {
        this.ribLength = ribLength;
    }

    public ListRibs getListRibs() {
        return listRibs;
    }

    public void setListRibs(ListRibs listRibs) {
        this.listRibs = listRibs;
    }

    private Integer ribLength;

    @ManyToOne
    @JoinColumn(name="Id_ListRibs")
    private ListRibs listRibs;


}