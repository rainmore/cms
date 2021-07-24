package com.rainmore.cms.domains.system.security.auth;

import com.rainmore.cms.domains.HasId;
import com.rainmore.cms.domains.users.Account;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "system.security.auth.loginToken")
@Table(name = "userLoginTokens")
public class LoginToken implements HasId<String> {

    private String id;
    private String        token;
    private Account       account;
    private LocalDateTime lastUsedAt;

    public LoginToken() {
    }

    public LoginToken(String id, String token, Account account, LocalDateTime lastUsedAt) {
        this.id = id;
        this.token = token;
        this.account = account;
        this.lastUsedAt = lastUsedAt;
    }

    @Id
    @Column(name = "series", nullable = false)
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "token", updatable = false, nullable = false)
    @NotEmpty
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne
    @JoinColumn(name = "userAccountId", updatable = false, nullable = false)
    @NotNull
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "lastUsedAt", nullable = false)
    @NotNull
    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(LocalDateTime lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

}
