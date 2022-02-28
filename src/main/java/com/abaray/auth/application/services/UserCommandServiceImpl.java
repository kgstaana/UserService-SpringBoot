package com.abaray.auth.application.services;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;
import com.abaray.auth.core.repositories.services.UserCommandService;
import com.abaray.auth.core.usecases.ChangeAccountStatus;
import com.abaray.auth.core.usecases.CreateUserUseCase;
import com.abaray.auth.core.usecases.DeleteUserUseCase;
import com.abaray.auth.core.usecases.UpdateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    @Autowired
    private UserCommandRepository userCommandRepository;

    @Override
    public void createUser(User user) throws Exception {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userCommandRepository);
        createUserUseCase.execute(user);
    }

    @Override
    public void updateUser(String id, User user) throws Exception {
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userCommandRepository);
        updateUserUseCase.execute(id, user);
    }

    @Override
    public int deleteUserByUserId(String id) throws Exception {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userCommandRepository);
        return deleteUserUseCase.execute(id);
    }

    @Override
    public int changeAccountStatus(String id, AccountStatus accountStatus) throws Exception {
        ChangeAccountStatus changeAccountStatus = new ChangeAccountStatus(userCommandRepository);
        return changeAccountStatus.execute(id, accountStatus);
    }
}
