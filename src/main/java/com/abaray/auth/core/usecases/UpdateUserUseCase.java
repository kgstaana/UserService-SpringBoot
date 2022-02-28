package com.abaray.auth.core.usecases;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;

public class UpdateUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public UpdateUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public boolean execute(String id, User user) throws BadRequestException, UnexpectedException {
        try {
            boolean isUpdated = userCommandRepository.updateUser(id, user);

            if (!isUpdated) {
                throw new BadRequestException(UserErrorCode.USER_300400.name(), "Bad Request");
            }

            return true;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_300500.name(), "Unexpected error");
        }
    }
}
