package com.abaray.auth.dataprovider;

import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.exceptions.NotFoundException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import com.abaray.auth.core.repositories.persistence.UserQueryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service()
@Qualifier("MongoDBUserQueryRepositoryImpl")
public class MongoDBUserQueryRepositoryImpl implements UserQueryRepository {

    @Override
    public PaginatedList<User> findUsers(int limit, int offset) throws UnexpectedException {
        return null;
    }

    @Override
    public User findUserById(String id) throws NotFoundException, UnexpectedException {
        return null;
    }
}
