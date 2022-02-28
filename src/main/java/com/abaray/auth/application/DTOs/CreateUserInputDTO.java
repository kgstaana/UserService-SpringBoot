package com.abaray.auth.application.DTOs;

import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.core.enums.UserRole;

public class CreateUserInputDTO {
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    public CreateUserInputDTO() {}

    public CreateUserInputDTO(String firstName, String lastName, UserRole userRole, AccountStatus accountStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.accountStatus = accountStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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
