package org.sguydye.sfservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.sguydye.sfservice.util.FieldType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Field")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogicalField extends GenericEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entityID", nullable = false)
    private LogicalEntity entity;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private FieldType type;

    @Column(name = "length")
    private Byte length;


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

    public Byte getLength() {
        return length;
    }

    public void setLength(Byte length) {
        this.length = length;
    }

    public Byte getMantissa() {
        return mantissa;
    }

    public void setMantissa(Byte mantissa) {
        this.mantissa = mantissa;
    }

    @Override
    public String toString() {
        return "LogicalField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", length=" + length +
                ", mantissa=" + mantissa +
                '}';
    }
}
