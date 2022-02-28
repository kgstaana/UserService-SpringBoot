package com.kenjie.auth.core.entities;

import com.kenjie.auth.core.enums.AccountStatus;
import com.kenjie.auth.core.enums.UserRole;

public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private AccountStatus accountStatus;

    public User() {}

    public User(String userId, String firstName, String lastName, UserRole userRole, AccountStatus accountStatus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.accountStatus = accountStatus;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
