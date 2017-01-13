package org.sguydye.sfservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdDate", "lastModified"}, allowGetters = true)
public abstract class GenericEntity {

    @Column(name = "created")
    private LocalDateTime createdDate;

    @Column(name = "lastModified")
    private LocalDateTime lastModified;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

}
