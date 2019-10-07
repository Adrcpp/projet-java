package com.persistence.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User")
@NamedQueries({ 
    @NamedQuery(name = "User.findAll", query = "SELECT user FROM User user"), 
    @NamedQuery(name = "User.findByUsername", query = "SELECT user FROM User user WHERE user.username = :username"), 
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer idUser;

    @Column(name = "civility")
    private String civility;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "dateBirth")
    private Date dateBirth;

    @Column(name = "dateCreation")
    private Date dateCreation;
   
    @Column(name = "dateModification")
    private Date dateModification;

    @Column(name = "active")
    private Boolean active;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name="idRole", nullable=false)
    private Role role;

    @JsonManagedReference
    @OneToMany(
        //cascade = {CascadeType.PERSIST},
        fetch = FetchType.EAGER, mappedBy="user",
        orphanRemoval = true
    )
    private List<Marker> markers = new ArrayList<>();

    public User() {
    }

    @PreUpdate
    public void preUpdate()
    {
        this.dateModification = new Date();
    }

    @PrePersist
    public void prePersist()
    {
        this.dateModification = new Date();
        this.dateCreation = new Date();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @XmlTransient
    public List<Marker> getMarkers() {
        return markers;
    }

    public void setMarkersSet(List<Marker> markers) {
        this.markers = markers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return String.format("\n[idUtilisateur=%s, civilite=%s, prenom=%s, nom=%s, identifiant=%s, motPasse=%s, dateNaissance=%s, dateCreation=%s, dateModification=%s, actif=%s, marquerEffacer=%s ,version=%s]\n", idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification, actif, marquerEffacer, version);
        return "";
    }
}