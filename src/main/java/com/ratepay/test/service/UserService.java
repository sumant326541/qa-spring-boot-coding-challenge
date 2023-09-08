/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratepay.test.exception.UnknownUserException;
import com.ratepay.test.model.User;
import com.ratepay.test.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save( final User user ) {
        userRepository.save( user );
    }

    public User findByUsername( final String username ) {
        return userRepository.findByUsername( username ).orElseThrow( UnknownUserException::new );
    }

    public boolean exists( final User user ) {
        return userRepository.findByUsername( user.getUsername() ).isPresent();
    }
}
