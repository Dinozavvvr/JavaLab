package ru.itis.javalab.security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;


/**
 * created: 28-03-2021 - 22:32
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
@EnableJdbcHttpSession
public class SessionConfiguration extends AbstractHttpSessionApplicationInitializer {

    /* it is unusable code */

    /*@Bean
    @SpringSessionDataSource
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("org/springframework/session/jdbc/schema-postgresql.sql")
                .build();
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

}
