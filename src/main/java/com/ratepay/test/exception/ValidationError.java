/*
 * This document contains trade secret data which are the property of
 * RatePAY GmbH, Berlin, Germany. Information contained herein must not be used,
 * copied or disclosed in whole or part unless permitted in writing by RatePAY GmbH. 
 * All rights reserved by RatePAY GmbH.
 *
 * Copyright (C) 2019 RatePAY GmbH / Berlin / Germany
 */
package com.ratepay.test.exception;


public class ValidationError extends RuntimeException {

    private static final long serialVersionUID = -5439425416362618L;

    public ValidationError( final String errorMessage ) {
        super( errorMessage );
    }
}
