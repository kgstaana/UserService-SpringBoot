package com.abaray.auth.application.services;

import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.repositories.persistence.UserQueryRepository;
import com.abaray.auth.core.repositories.services.UserQueryService;
import com.abaray.auth.core.usecases.GetUserByIdUseCase;
import com.abaray.auth.core.usecases.GetUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    @Qualifier("PgSQLUserQueryRepositoryImpl")
    private UserQueryRepository userQueryRepository;

    @Override
    public PaginatedList<User> getUsers(int limit, int offset) throws Exception {
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(userQueryRepository);
        return getUsersUseCase.execute(limit, offset);
    }

    @Override
    public User getUserById(String id) throws Exception {
        GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(userQueryRepository);
        return getUserByIdUseCase.execute(id);
    }
}
