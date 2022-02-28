package com.abaray.auth.core.usecases;

import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;

public class DeleteUserUseCase {

    private final UserCommandRepository userCommandRepository;

    public DeleteUserUseCase(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public int execute(String userId) throws BadRequestException, UnexpectedException {
        try {
            int updatedRows = userCommandRepository.deleteUserByUserId(userId);

            if (updatedRows == 0) {
                throw new BadRequestException(UserErrorCode.USER_400400.name(), "Bad Request");
            }

            return updatedRows;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_400500.name(), "Unexpected error");
        }
    }
}
