/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratepay.test.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername( String username );
}
