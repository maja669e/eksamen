package sogne.maja.eksamen.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commune")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String communeCode;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commune")
    private Set<Parish> parishes = new HashSet<>();

    public Commune(){}

    public Commune(long id) {
        this.id = id;
    }

    public Commune(long id, String name, String communeCode, Set<Parish> parishes) {
        this.id = id;
        this.name = name;
        this.communeCode = communeCode;
        this.parishes = parishes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public Set<Parish> getParishes() {
        return parishes;
    }

    public void setParishes(Set<Parish> parishes) {
        this.parishes = parishes;
    }
}
