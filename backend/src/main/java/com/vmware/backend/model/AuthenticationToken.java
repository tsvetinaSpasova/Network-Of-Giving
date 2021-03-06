package com.vmware.backend.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authentication_token")
public final class AuthenticationToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    private User user;

    private Date expireDate;

    protected AuthenticationToken()
    {
        /* Reflection instantiation */
    }

    public AuthenticationToken(User user, String token)
    {
        this.user = user;
        this.token = token;
    }

    public AuthenticationToken(User user, String token, Date expireDate)
    {
        this(user, token);
        this.expireDate = expireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(final Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isExpired()
    {
        if (null == this.expireDate) {
            return false;
        }

        return this.expireDate.getTime() > System.currentTimeMillis();
    }
}