/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.model;


import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table( name = "user_account" )
public class User {

    private static final String USER_ID_SEQUENCE_NAME = "user_id_sequence";
    private static final String GENERATOR_NAME = "user-id-sequence";

    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = GENERATOR_NAME )
    @SequenceGenerator( sequenceName = USER_ID_SEQUENCE_NAME, name = GENERATOR_NAME )
    private Long id;

    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( final String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( final String password ) {
        this.password = password;
    }
}
