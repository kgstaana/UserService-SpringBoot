package com.kenjie.auth.core.repositories;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.exceptions.BadRequestException;
import com.kenjie.auth.core.exceptions.DuplicateRecordException;
import com.kenjie.auth.core.exceptions.UnexpectedException;

public interface UserCommandRepository {

    void insertUser(User user) throws DuplicateRecordException, UnexpectedException;

    boolean updateUser(String id, User user)  throws BadRequestException, UnexpectedException;

    int deleteUserByUserId(String userId) throws UnexpectedException;
}
