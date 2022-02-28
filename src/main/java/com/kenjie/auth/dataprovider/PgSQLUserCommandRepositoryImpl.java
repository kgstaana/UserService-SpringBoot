package com.kenjie.auth.dataprovider;

import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.core.enums.AccountStatus;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.DuplicateRecordException;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserCommandRepository;
import com.kenjie.auth.dataprovider.orm.models.UserModel;
import com.kenjie.auth.dataprovider.orm.repositories.UserJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PgSQLUserCommandRepositoryImpl implements UserCommandRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void insertUser(User user) throws DuplicateRecordException, UnexpectedException {
        try {
            UserModel userDetails = modelMapper.map(user, UserModel.class);
            this.userJpaRepository.saveAndFlush(userDetails);
        } catch (Exception e) {
            Throwable t = e.getCause();

            if (t instanceof ConstraintViolationException) {
                throw new DuplicateRecordException(UserErrorCode.USER_200422.name(), "Duplicated Record");
            }

            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }

    public boolean updateUser(String id, User user) throws UnexpectedException {
        try {
            UserModel userDetails = this.userJpaRepository.findByUserId(id);

            if (userDetails != null) {
                modelMapper.map(user, userDetails);
                this.userJpaRepository.saveAndFlush(userDetails);
                return true;
            }

            return false;
        } catch (Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }

    public int deleteUserByUserId(String userId) throws UnexpectedException {
        try {
            return this.userJpaRepository.deleteByUserId(userId);
        } catch(Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_400500.name(), "Unexpected error");
        }
    }
}
