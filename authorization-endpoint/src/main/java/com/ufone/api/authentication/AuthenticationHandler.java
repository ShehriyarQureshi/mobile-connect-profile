/*
 * Copyright (c) 2019 Muhammad Shehriyar Qureshi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.ufone.api.authentication;

import com.ufone.api.request.AuthorizationServerRequest;
import com.ufone.api.validation.CodeRequestValidation;
import com.ufone.api.authorization_code.AuthorizationCodeResponse;
import com.ufone.api.errors.AuthenticationFailed;
import javax.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

/*
 * Responsible for calling PolicyEngine and returning authentication response.
 */
public abstract class AuthenticationHandler {
        /*
         * Method which calls policy engine and returns authentication response.
         *
         * @param request Request object which is used to get query parameter values
         *
         * @throws UnsupportedEncodingException when encoding strings to urlencoding fails
         */
        public abstract Response handler(AuthorizationServerRequest request);
}
