package com.tunnel.entity;

import javax.persistence.*;

@Entity
@Table(name="user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserRole {

    @EmbeddedId
    private UserRoleId userRoleId;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    private Role role;

    public UserRole() {
    }

    public UserRole(UserRoleId userRoleId, User user, Role role) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.role = role;
    }

    public UserRoleId getUserRoleId() {
        return userRoleId;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public void setUserRoleId(UserRoleId userRoleId) {
        this.userRoleId = userRoleId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
