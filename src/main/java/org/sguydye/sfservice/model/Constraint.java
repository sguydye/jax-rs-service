package org.sguydye.sfservice.model;

import org.sguydye.sfservice.util.ConstraintType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Constraints")
public class Constraint {

    public Constraint() {
    }

    public Constraint(ConstraintType type) {
        this.type = type;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private ConstraintType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConstraintType getType() {
        return type;
    }

    public void setType(ConstraintType type) {
        this.type = type;
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
