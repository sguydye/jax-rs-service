package org.sguydye.sfservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Entity")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LogicalEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dbName")
    private String dbName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "entity")
    private Set<LogicalField> fields;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "entity")
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

    public Set<LogicalField> getFields() {
        return fields;
    }

    public void setFields(Set<LogicalField> fields) {
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
