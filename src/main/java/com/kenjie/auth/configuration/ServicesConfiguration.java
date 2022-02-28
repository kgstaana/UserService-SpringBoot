package com.kenjie.auth.configuration;

import com.kenjie.auth.core.services.UserCommandService;
import com.kenjie.auth.core.services.UserQueryService;
import com.kenjie.auth.dataprovider.PgSQLUserCommandRepositoryImpl;
import com.kenjie.auth.dataprovider.PgSQLUserQueryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Autowired
    public PgSQLUserQueryRepositoryImpl pgSQLUserQueryRepositoryImpl;

    @Autowired
    public PgSQLUserCommandRepositoryImpl pgSQLUserCommandRepositoryImpl;

    @Bean()
    public UserQueryService userQueryService() {
        return new UserQueryService(pgSQLUserQueryRepositoryImpl);
    }

    @Bean()
    public UserCommandService userCommandService() {
        return new UserCommandService(pgSQLUserCommandRepositoryImpl);
    }
}
