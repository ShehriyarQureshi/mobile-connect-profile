// Copyright 2019 Shehriyar Qureshi
package com.ufone.api.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.lang.Thread;

import com.google.gson.Gson;

import com.ufone.api.util.ErrorResponse;
import com.ufone.api.authentication.ClientValidation;

import com.ufone.api.authentication.AuthenticationMethods;
import com.ufone.api.authentication.UserAuthenticationHandler;

@Path("/")
public class AuthenticationEndPointHandler {
        @GET
        @Path("authorize")
        @Produces(MediaType.APPLICATION_JSON)
        public Response ReturnParam(@QueryParam("response_type") String response_type,
                        @QueryParam("scope") String scope, @QueryParam("client_id") String client_id,
                        @QueryParam("redirect_uri") String redirect_uri, @QueryParam("state") String state,
                        @QueryParam("verson") String mc_version, @QueryParam("nonce") String nonce) {

                if (response_type != null && client_id != null && redirect_uri != null && scope != null) {
                        try {
                                ClientValidation clientValidation = new ClientValidation(client_id, redirect_uri);
                                // if client is validated, only then initiate authentication
                                if (clientValidation.isClientValid() == true) {
                                        // display wait screen & start authentication stuff
                                        // need to work this out
                                        UserAuthenticationHandler userAuthenticationHandler = new UserAuthenticationHandler();
                                        userAuthenticationHandler.redirectToWaitScreen();
                                        AuthenticationMethods authenticationMethod = new AuthenticationMethods();
                                        boolean authenticationStatus = authenticationMethod.USSDAuthentication();
                                        if (authenticationStatus == true) {
                                                // generate and return code
                                                return Response.status(302).entity("code=some_code").build();
                                        } else if (authenticationStatus == false) {
                                                return Response.status(302).entity("Auth Failed").build();
                                        } else {
                                                return Response.status(400).entity("Server Error").build();
                                        }
                                } else {
                                        return Response.status(302).entity("Wrong").build();
                                }
                        } catch (Exception serverError) {
                                Gson jsonResponse = new Gson();
                                ErrorResponse errorResponse = new ErrorResponse("server Error",
                                                "something went wrong while processing request");
                                String responseBody = jsonResponse.toJson(errorResponse);
                                return Response.status(400).entity(responseBody).build();
                        }
                        // TODO: these are temporary, the errors should be raised during validation
                        // of the request
                } else if (response_type == null) {
                        Gson jsonResponse = new Gson();
                        ErrorResponse errorResponse = new ErrorResponse("invalid_request", "response_type is invalid");
                        String responseBody = jsonResponse.toJson(errorResponse);
                        return Response.status(400).entity(responseBody).build();
                } else if (scope == null) {
                        Gson jsonResponse = new Gson();
                        ErrorResponse errorResponse = new ErrorResponse("invalid_request", "scope is invalid");
                        String responseBody = jsonResponse.toJson(errorResponse);
                        return Response.status(400).entity(responseBody).build();
                } else if (client_id == null) {
                        Gson jsonResponse = new Gson();
                        ErrorResponse errorResponse = new ErrorResponse("invalid_request", "client_id is invalid");
                        String responseBody = jsonResponse.toJson(errorResponse);
                        return Response.status(400).entity(responseBody).build();
                } else if (redirect_uri == null) {
                        Gson jsonResponse = new Gson();
                        ErrorResponse errorResponse = new ErrorResponse("invalid_request", "redirect_uri is invalid");
                        String responseBody = jsonResponse.toJson(errorResponse);
                        return Response.status(400).entity(responseBody).build();
                } else {
                        Gson jsonResponse = new Gson();
                        ErrorResponse errorResponse = new ErrorResponse("something went wrong",
                                        "request might be invalid");
                        String responseBody = jsonResponse.toJson(errorResponse);
                        return Response.status(400).entity(responseBody).build();
                }
        }
}
