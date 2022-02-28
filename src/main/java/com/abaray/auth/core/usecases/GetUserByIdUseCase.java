package com.abaray.auth.core.usecases;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.NotFoundException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserQueryRepository;

public class GetUserByIdUseCase {

    private final UserQueryRepository userQueryRepository;

    public GetUserByIdUseCase(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public User execute(String id) throws NotFoundException, UnexpectedException {
        try {
            User user = userQueryRepository.findUserById(id);

            if (user == null) {
                throw new NotFoundException(UserErrorCode.USER_100404.name(), "User not exist");
            }

            return user;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_100500.name(), "Unexpected error");
        }
    }
}
