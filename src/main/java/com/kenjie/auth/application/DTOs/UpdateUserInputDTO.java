package com.kenjie.auth.application.DTOs;

public class UpdateUserInputDTO {
    private String firstName;
    private String lastName;

    public UpdateUserInputDTO() {}

    public UpdateUserInputDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
