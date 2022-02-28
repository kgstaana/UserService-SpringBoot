package com.abaray.auth.core.usecases;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.DuplicateRecordException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;

public class ChangeAccountStatus {
    private final UserCommandRepository userCommandRepository;

    public ChangeAccountStatus(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public int execute(String id, AccountStatus status) throws BadRequestException, UnexpectedException {
        try {
            int updatedRows = userCommandRepository.changeAccountStatus(id, status);

            System.out.println("...updatedRows" + updatedRows);

            if (updatedRows == 0) {
                throw new BadRequestException(UserErrorCode.USER_ACCOUNT_500400.name(), "Bad Request");
            }
            return updatedRows;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_ACCOUNT_500500.name(), "Unexpected error");
        }
    }
}
