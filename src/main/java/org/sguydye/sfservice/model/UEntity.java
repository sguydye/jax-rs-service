package org.sguydye.sfservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Entity")
public class UEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "dbName")
    private String dbName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "entity")
    private List<Field> fields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "entity")
    private List<Constraint> constraints;

    public UEntity() {
    }

    public UEntity(String name) {
        this.name = name;
    }

    public UEntity(String name, String dbName) {
        this.name = name;
        this.dbName = dbName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UEntity uEntity = (UEntity) o;

        if (id != uEntity.id) return false;
        return name.equals(uEntity.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
