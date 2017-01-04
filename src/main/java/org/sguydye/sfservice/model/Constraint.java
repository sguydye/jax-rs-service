package org.sguydye.sfservice.model;

import org.sguydye.sfservice.util.ConstraintType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Constraints")
public class Constraint {


    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entityID")
    private UEntity entity;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ConstraintType type;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Field> fields;

    public Constraint() {
    }

    public Constraint(ConstraintType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UEntity getEntity() {
        return entity;
    }

    public void setEntity(UEntity entity) {
        this.entity = entity;
    }

    public ConstraintType getType() {
        return type;
    }

    public void setType(ConstraintType type) {
        this.type = type;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint that = (Constraint) o;

        if (id != that.id) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }
}
