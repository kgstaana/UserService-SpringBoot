package com.kenjie.auth.core.services.usecases;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.BadRequestException;
import com.kenjie.auth.core.exceptions.DuplicateRecordException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserCommandRepository;

public class UpdateUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public UpdateUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public boolean execute(String id, User user) throws BadRequestException, UnexpectedException {
        try {
            boolean isUpdated = this.userCommandRepository.updateUser(id, user);

            if (!isUpdated) {
                throw new BadRequestException(UserErrorCode.USER_300400.name(), "Bad Request");
            }

            return true;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_300500.name(), "Unexpected error");
        }
    }
}
