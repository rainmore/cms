package com.rainmore.cms.domains.users;

import com.rainmore.cms.domains.Auditable;
import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.Nameable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "users.accounts")
@Table(name = "userAccounts")
public class Account extends Auditable implements HasId<Long>, Nameable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountStatus status = AccountStatus.SUSPENDED;
    private LocalDateTime lastLoginAt;
    private Set<Role>       roles;
    private Set<Permission> permissions;

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
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    @NotEmpty
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Column
    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @ManyToMany
    @JoinTable(name = "userAccountsRoles",
            joinColumns = @JoinColumn(name = "userAccountId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userRoleId", nullable = false, updatable = false))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name = "userAccountsPermissions",
            joinColumns = @JoinColumn(name = "userAccountId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userPermissionId", nullable = false, updatable = false))
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Transient
    @Override
    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }
}
