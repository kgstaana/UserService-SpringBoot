package com.abaray.auth.dataprovider.orm.repositories;

import com.abaray.auth.core.enums.AccountStatus;
import com.abaray.auth.dataprovider.orm.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUserId(String userId);

    @Query(
        value = "SELECT id, user_id, first_name, last_name, user_role, account_status FROM users LIMIT :limit OFFSET :offset",
        nativeQuery = true
    )
    List<UserModel> findUsers(
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM users u WHERE u.user_id = :userId",
        nativeQuery = true
    )
    int deleteByUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE users SET account_status = :accountStatus WHERE user_id = :userId",
        nativeQuery = true
    )
    int changeAccountStatus(
        @Param("userId") String userId,
        @Param("accountStatus") String accountStatus
    );
}
