/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test;


import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


@SpringBootApplication( scanBasePackages = { "com.ratepay.test" } )
public class SpringBootCodingChallenge {

    public static void main( String[] args ) {
        SpringApplication.run( SpringBootCodingChallenge.class, args );
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType( HSQL ).build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate( final DataSource dataSource ) {
        return new JdbcTemplate( dataSource );
    }
}
