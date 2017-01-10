package org.sguydye.sfservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.sguydye.sfservice.util.FieldType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Field")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LogicalField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entityID")
    private LogicalEntity entity;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private FieldType type;

    @JsonIgnore
    @Column(name = "length")
    private Byte length;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "mantissa")
    private Byte mantissa;

    public LogicalField() {
    }

    public LogicalField(String name, FieldType type) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogicalField logicalField = (LogicalField) o;

        if (id != logicalField.id) return false;
        if (!name.equals(logicalField.name)) return false;
        return type == logicalField.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LogicalField{" +
                "id=" + id +
                ", entity=" + entity +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", length=" + length +
                ", mantissa=" + mantissa +
                '}';
    }
}
