package com.rainmore.cms.domains.system;

import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.Nameable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "system.properties")
@Table(name = "systemProperties")
public class SystemProperty implements HasId<String>, Nameable {
    private String id;
    private String data;

    public SystemProperty() {
    }

    public SystemProperty(String id, String data) {
        this.id = id;
        this.data = data;
    }

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    @NotEmpty
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false)
    @NotEmpty
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Transient
    @Override
    public String getName() {
        return null;
    }
}
