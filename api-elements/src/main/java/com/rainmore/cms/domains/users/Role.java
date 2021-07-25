package com.rainmore.cms.domains.users;

import com.rainmore.cms.domains.Auditable;
import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.Nameable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "users.role")
@Table(name = "userRoles")
public class Role extends Auditable implements HasId<Long>, Nameable {

    private Long            id;
    private String          name;
    private Role            parent;
    private Set<Permission> permissions;
    private Boolean         almighty = false;

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
    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    @ManyToMany
    @JoinTable(name = "userRolesPermissions",
            joinColumns = @JoinColumn(name = "userRoleId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userPermissionId",  nullable = false, updatable = false))
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Column(name="isAlmighty", nullable = false)
    @NotNull
    public Boolean isAlmighty() {
        return almighty;
    }

    public void setAlmighty(Boolean almighty) {
        this.almighty = almighty;
    }
}
