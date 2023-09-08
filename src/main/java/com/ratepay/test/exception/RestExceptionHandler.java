/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.exception;


import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order( HIGHEST_PRECEDENCE )
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( ValidationError.class )
    protected ResponseEntity<String> handleValidationError( final ValidationError ex ) {
        return ResponseEntity.unprocessableEntity().body( ex.getMessage() );
    }

    @SuppressWarnings( "unused" )
    @ExceptionHandler( UnknownUserException.class )
    protected ResponseEntity<String> handleUnknownUserError( final UnknownUserException ex ) {
        return ResponseEntity.notFound().build();
    }
}