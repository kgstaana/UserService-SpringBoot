package com.abaray.auth.application.DTOs;

import com.abaray.auth.core.enums.AccountStatus;

public class AccountStatusDTO {

    private AccountStatus accountStatus;

    public AccountStatusDTO() {}

    public AccountStatusDTO(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
