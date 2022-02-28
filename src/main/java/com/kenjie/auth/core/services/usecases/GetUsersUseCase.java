package com.kenjie.auth.core.services.usecases;

import com.kenjie.auth.core.entities.PaginatedList;
import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.BadRequestException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserQueryRepository;

import java.util.List;

public class GetUsersUseCase {

    private final UserQueryRepository userQueryRepository;

    public GetUsersUseCase(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public PaginatedList<User> execute(int limit, int offset) throws BadRequestException, UnexpectedException {
        try {
            if (limit == 0) {
                throw new BadRequestException(UserErrorCode.USER_LIST_100400.name(), "Limit must be greater than zero");
            }

            return this.userQueryRepository.findUsers(limit, offset);
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_LIST_100500.name(), "Unexpected error");
        }
    }
}
