package org.sguydye.sfservice.model;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Entity")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LogicalEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "dbName")
    private String dbName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "entity")
    @JsonManagedReference
    private List<LogicalField> fields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "entity")
    private List<LogicalConstraint> constraints;

    public LogicalEntity() {
    }

    public LogicalEntity(String name) {
        this.name = name;
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

    public List<LogicalField> getFields() {
        return fields;
    }

    public void setFields(List<LogicalField> fields) {
        this.fields = fields;
    }

    public List<LogicalConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<LogicalConstraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogicalEntity that = (LogicalEntity) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LogicalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dbName='" + dbName + '\'' +
                ", fields=" + fields +
                ", constraints=" + constraints +
                '}';
    }
}
