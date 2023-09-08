/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ratepay.test.exception.ValidationError;
import com.ratepay.test.model.User;
import com.ratepay.test.service.UserService;


@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public void validate( final User user ) {
        if( exists( user ) ) {
            throw new ValidationError( "User already registered" );
        }

        if( user.getUsername().length() < 6 || user.getUsername().length() > 32 ) {
            throw new ValidationError( "username is not valid" );
        }

        if( user.getPassword().length() < 8 || user.getPassword().length() > 32 ) {
            throw new ValidationError( "password is not secure" );
        }
    }

    public boolean exists( final User user ) {
        return userService.exists( user );
    }
}
