package com.abaray.auth.core.usecases;

import com.abaray.auth.core.exceptions.DuplicateRecordException;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;

public class CreateUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public CreateUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public void execute(User user) throws DuplicateRecordException, UnexpectedException {
        try {
            userCommandRepository.insertUser(user);
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }
}
