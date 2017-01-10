package org.sguydye.sfservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.sguydye.sfservice.util.ConstraintType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "[Constraint]")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LogicalConstraint implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entityID")
    private LogicalEntity entity;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ConstraintType type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Constraint_Field",
            joinColumns = @JoinColumn(name = "constraintID"),
            inverseJoinColumns = @JoinColumn(name = "fieldID")
    )
    private Set<LogicalField> fields;

    public LogicalConstraint() {
    }

    public LogicalConstraint(ConstraintType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LogicalEntity getEntity() {
        return entity;
    }

    public void setEntity(LogicalEntity entity) {
        this.entity = entity;
    }

    public ConstraintType getType() {
        return type;
    }

    public void setType(ConstraintType type) {
        this.type = type;
    }

    public Set<LogicalField> getFields() {
        return fields;
    }

    public void setFields(Set<LogicalField> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogicalConstraint that = (LogicalConstraint) o;

        if (id != that.id) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LogicalConstraint{" +
                "id=" + id +
                ", entity=" + entity +
                ", type=" + type +
                ", fields=" + fields +
                '}';
    }
}