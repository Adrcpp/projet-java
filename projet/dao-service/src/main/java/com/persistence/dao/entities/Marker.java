package com.persistence.dao.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Marker")
@NamedQueries({ 
    @NamedQuery(name = "Marker.findAll", query = "SELECT marker FROM Marker marker"), 
    @NamedQuery(name = "Marker.findMarkersByUserId", query = "SELECT marker FROM Marker marker JOIN marker.user user WHERE user.idUser = :idUser")
})
public class Marker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer idMarker;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="x")
    private String x;

    @Column(name="y")
    private String y;

    @Column(name="isPrivate")
    private Boolean isPrivate;

    @Version
    private Integer version;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    public Marker() {
    }

    public Integer getIdMarker() {
        return idMarker;
    }

    public void setIdMarker(Integer idMarker) {
        this.idMarker = idMarker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;  
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarker != null ? idMarker.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Marker other = (Marker) object;
        if ((this.idMarker == null && other.idMarker != null) || (this.idMarker != null && !this.idMarker.equals(other.idMarker))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // return String.format("[idAdresse=%s , rue=%s , ville=%s , codePostal=%s , pays=%s , principale=%s , version=%s, idUtilisateur=%s]\n", idAdresse, rue, ville, codePostal, pays, principale, version, idUtilisateur);
        return "";
    }
}