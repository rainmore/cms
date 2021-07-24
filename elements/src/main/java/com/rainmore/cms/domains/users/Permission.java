package com.rainmore.cms.domains.users;

import com.rainmore.cms.domains.Auditable;
import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.Nameable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "users.permission")
@Table(name = "userPermissions")
public class Permission extends Auditable implements HasId<Long>, Nameable {

    private Long id;
    private String name;
    private Permission parent;

    @Id
    @Column(nullable = false)
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Override
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "parentId")
    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }
}
