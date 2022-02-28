package com.abaray.auth.core.repositories.services;

import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;


public interface UserQueryService {
    PaginatedList<User> getUsers(int limit, int offset) throws Exception;

    User getUserById(String id) throws Exception;
}
