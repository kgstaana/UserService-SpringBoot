package com.abaray.auth.core.repositories.services;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.AccountStatus;

public interface UserCommandService {
    void createUser(User user) throws Exception;

    void updateUser(String id, User user) throws Exception;

    int deleteUserByUserId(String id) throws Exception;

    int changeAccountStatus(String id, AccountStatus accountStatus) throws Exception;
}
