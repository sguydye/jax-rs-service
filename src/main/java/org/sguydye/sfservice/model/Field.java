package org.sguydye.sfservice.model;

import org.sguydye.sfservice.util.FieldType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Fields")
public class Field {

    public Field() {
    }

    public Field(String name, FieldType type) {
        this.name = name;
        this.type = type;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entityID")
    private UEntity entity;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private FieldType type;

    @Column(name = "length")
    private byte length;

    @Column(name = "mantissa")
    private byte mantissa;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Fields_Constraints", joinColumns = @JoinColumn(name = "fieldID"),
            inverseJoinColumns = @JoinColumn(name = "constraintID"))
    private Set<Constraint> constraints;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public byte getLength() {
        return length;
    }

    public void setLength(byte length) {
        this.length = length;
    }

    public byte getMantissa() {
        return mantissa;
    }

    public void setMantissa(byte mantissa) {
        this.mantissa = mantissa;
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(Set<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (id != field.id) return false;
        if (length != field.length) return false;
        if (!name.equals(field.name)) return false;
        return type == field.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (int) length;
        return result;
    }
}
