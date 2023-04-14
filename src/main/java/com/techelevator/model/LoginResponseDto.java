package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
public class LoginResponseDto {

    private String token;
    private Users users;

    public LoginResponseDto(String token, Users users) {
        this.token = token;
        this.users = users;
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
