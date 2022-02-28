package com.kenjie.auth.core.services;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.repositories.UserCommandRepository;
import com.kenjie.auth.core.services.usecases.CreateUserUseCase;
import com.kenjie.auth.core.services.usecases.DeleteUserUseCase;
import com.kenjie.auth.core.services.usecases.UpdateUserUseCase;

public class UserCommandService {

    private final UserCommandRepository userCommandRepository;

    public UserCommandService(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public void createUser(User user) throws Exception {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userCommandRepository);
        createUserUseCase.execute(user);
    }

    public void updateUser(String id, User user) throws Exception {
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userCommandRepository);
        updateUserUseCase.execute(id, user);
    }

    public int deleteUserByUserId(String id) throws Exception {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userCommandRepository);
        return deleteUserUseCase.execute(id);
    }
}
