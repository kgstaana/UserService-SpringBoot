package com.kenjie.auth.core.services;

import com.kenjie.auth.core.entities.PaginatedList;
import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.repositories.UserQueryRepository;
import com.kenjie.auth.core.services.usecases.GetUserByIdUseCase;
import com.kenjie.auth.core.services.usecases.GetUsersUseCase;

import java.util.List;

public class UserQueryService {

    private final UserQueryRepository userQueryRepository;

    public UserQueryService(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public PaginatedList<User> getUsers(int limit, int offset) throws Exception {
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(userQueryRepository);
        return getUsersUseCase.execute(limit, offset);
    }

    public User getUserById(String id) throws Exception {
        GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(userQueryRepository);
        return getUserByIdUseCase.execute(id);
    }
}

