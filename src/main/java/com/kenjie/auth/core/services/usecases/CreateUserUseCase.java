package com.kenjie.auth.core.services.usecases;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.DuplicateRecordException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserCommandRepository;

public class CreateUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public CreateUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public void execute(User user) throws DuplicateRecordException, UnexpectedException {
        try {
            this.userCommandRepository.insertUser(user);
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }
}
