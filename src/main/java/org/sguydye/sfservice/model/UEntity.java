package org.sguydye.sfservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Entities")
public class UEntity {

    public UEntity() {
    }

    public UEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "userID")
    private int userID;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "entity")
    private List<Field> fields;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UEntity uEntity = (UEntity) o;

        if (id != uEntity.id) return false;
        if (userID != uEntity.userID) return false;
        return name.equals(uEntity.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userID;
        result = 31 * result + name.hashCode();
        return result;
    }
}
