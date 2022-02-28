package com.kenjie.auth.core.services.usecases;

import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.BadRequestException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserCommandRepository;

public class DeleteUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public DeleteUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public int execute(String userId) throws BadRequestException, UnexpectedException {
        try {
            int updatedRows = this.userCommandRepository.deleteUserByUserId(userId);

            if (updatedRows == 0) {
                throw new BadRequestException(UserErrorCode.USER_400400.name(), "Bad Request");
            }

            return updatedRows;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_400500.name(), "Unexpected error");
        }
    }
}
