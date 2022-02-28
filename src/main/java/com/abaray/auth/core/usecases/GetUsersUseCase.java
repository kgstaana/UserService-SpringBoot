package com.abaray.auth.core.usecases;

import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserQueryRepository;

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

            return userQueryRepository.findUsers(limit, offset);
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_LIST_100500.name(), "Unexpected error");
        }
    }
}
