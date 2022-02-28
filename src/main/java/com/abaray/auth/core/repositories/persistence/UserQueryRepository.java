package com.abaray.auth.core.repositories.persistence;

import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.exceptions.NotFoundException;
import com.abaray.auth.core.exceptions.UnexpectedException;

public interface UserQueryRepository {

    PaginatedList<User> findUsers(int limit, int offset) throws UnexpectedException;

    User findUserById(String id) throws NotFoundException, UnexpectedException;
}
