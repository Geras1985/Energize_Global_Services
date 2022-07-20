package com.hotel.villa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * .
 */
@Entity
@Table(name = "user_keys")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UserKey implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @Column(nullable = false, name = "roles")
    private Role role;

    @NotNull
    @Column(nullable = false, name = "token_type")
    private String tokenType;

    @NotNull
    @Column(nullable = false, name = "token")
    private String token;

    @NotNull
    @Column(nullable = false, name = "refresh_token")
    private String refreshToken;

    @NotNull
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "expires_at")
    private Date expiresAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}

