/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.web;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratepay.test.model.User;
import com.ratepay.test.service.UserService;
import com.ratepay.test.validator.UserValidator;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping( value = "/users/{username}", method = GET )
    public ResponseEntity<?> getUserByName( @PathVariable( "username" ) final String username ) {
        return ResponseEntity.ok( userService.findByUsername( username ) );
    }

    @ResponseBody
    @RequestMapping( value = "/registration", method = POST )
    public ResponseEntity<?> createNewUser( @RequestBody final User user ) {
        userValidator.validate( user );

        userService.save( user );

        return ResponseEntity.status( CREATED ).build();
    }
}
