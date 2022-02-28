package com.abaray.auth.core.repositories.persistence;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.core.exceptions.DuplicateRecordException;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.UnexpectedException;

public interface UserCommandRepository {

    void insertUser(User user) throws DuplicateRecordException, UnexpectedException;

    boolean updateUser(String id, User user)  throws BadRequestException, UnexpectedException;

    int deleteUserByUserId(String userId) throws UnexpectedException;

    int changeAccountStatus(String id, AccountStatus accountStatus) throws UnexpectedException;
}
