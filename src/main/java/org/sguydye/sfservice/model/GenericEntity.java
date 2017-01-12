package org.sguydye.sfservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
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
