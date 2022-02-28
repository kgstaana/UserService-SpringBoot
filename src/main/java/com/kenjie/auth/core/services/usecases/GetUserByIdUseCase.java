package com.kenjie.auth.core.services.usecases;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.NotFoundException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserQueryRepository;

public class GetUserByIdUseCase {

    private final UserQueryRepository userQueryRepository;

    public GetUserByIdUseCase(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public User execute(String id) throws NotFoundException, UnexpectedException {
        try {
            User user = this.userQueryRepository.findUserById(id);

            if (user == null) {
                throw new NotFoundException(UserErrorCode.USER_100404.name(), "User not exist");
            }

            return user;
        } catch(UnexpectedException e) {
            throw new UnexpectedException(UserErrorCode.USER_100500.name(), "Unexpected error");
        }
    }
}
