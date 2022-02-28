package com.abaray.auth.dataprovider;

import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.DuplicateRecordException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserCommandRepository;
import com.abaray.auth.dataprovider.orm.models.UserModel;
import com.abaray.auth.dataprovider.orm.repositories.UserJpaRepository;
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

    @Override
    public void insertUser(User user) throws DuplicateRecordException, UnexpectedException {
        try {
            UserModel userDetails = modelMapper.map(user, UserModel.class);
            userJpaRepository.saveAndFlush(userDetails);
        } catch (Exception e) {
            Throwable t = e.getCause();

            if (t instanceof ConstraintViolationException) {
                throw new DuplicateRecordException(UserErrorCode.USER_200422.name(), "Duplicated Record");
            }

            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }

    @Override
    public boolean updateUser(String id, User user) throws UnexpectedException {
        try {
            UserModel userDetails = userJpaRepository.findByUserId(id);

            if (userDetails != null) {
                modelMapper.map(user, userDetails);
                userJpaRepository.saveAndFlush(userDetails);
                return true;
            }

            return false;
        } catch (Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_200500.name(), "Unexpected error");
        }
    }

    @Override
    public int deleteUserByUserId(String userId) throws UnexpectedException {
        try {
            return userJpaRepository.deleteByUserId(userId);
        } catch(Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_400500.name(), "Unexpected error");
        }
    }

    @Override
    public int changeAccountStatus(String id, AccountStatus accountStatus) throws UnexpectedException {
        try {
            return userJpaRepository.changeAccountStatus(id, accountStatus.name());
        } catch(Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_ACCOUNT_500500.name(), "Unexpected error");
        }
    }
}
