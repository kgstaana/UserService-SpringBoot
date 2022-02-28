package com.kenjie.auth.dataprovider;

import com.kenjie.auth.core.entities.PaginatedList;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.UnexpectedException;
import com.kenjie.auth.core.repositories.UserQueryRepository;
import com.kenjie.auth.core.entities.User;
import com.kenjie.auth.dataprovider.orm.models.UserModel;
import com.kenjie.auth.dataprovider.orm.repositories.UserJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PgSQLUserQueryRepositoryImpl implements UserQueryRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findUserById(String id) throws UnexpectedException {
        try {
            UserModel userDetails = this.userJpaRepository.findByUserId(id);

            if (userDetails != null) {
                return modelMapper.map(userDetails, User.class);
            }

            return null;
        } catch(Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_100500.name(), "Unexpected error");
        }
    }

    @Override
    public PaginatedList<User> findUsers(int limit, int offset) throws UnexpectedException {
        try {
            List<UserModel> userModelList = this.userJpaRepository.findUsers(limit + 1, offset * limit);
            boolean hasNextData = userModelList.size() > limit;
            int usersCount = hasNextData ? userModelList.size() - 1 : userModelList.size();

            List<User> users = userModelList.subList(0, usersCount)
                .stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());

            return new PaginatedList<>(users, hasNextData);
        } catch (Exception e) {
            throw new UnexpectedException(UserErrorCode.USER_100500.name(), "Unexpected error");
        }
    }
}
