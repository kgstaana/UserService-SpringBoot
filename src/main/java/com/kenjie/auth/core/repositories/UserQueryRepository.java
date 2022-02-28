package com.kenjie.auth.core.repositories;

import com.kenjie.auth.core.entities.PaginatedList;
import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.exceptions.NotFoundException;
import com.kenjie.auth.core.exceptions.UnexpectedException;

import java.util.List;

public interface UserQueryRepository {

    User findUserById(String id) throws NotFoundException, UnexpectedException;

    PaginatedList<User> findUsers(int limit, int offset) throws UnexpectedException;
}
