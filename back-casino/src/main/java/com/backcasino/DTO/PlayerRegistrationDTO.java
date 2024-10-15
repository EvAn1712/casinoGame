package com.backcasino.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerRegistrationDTO {
    private String username;
    private String password;
    private String email;

    public PlayerRegistrationDTO() {
    }

    public PlayerRegistrationDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
