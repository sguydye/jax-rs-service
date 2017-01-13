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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LogicalEntity extends GenericEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
