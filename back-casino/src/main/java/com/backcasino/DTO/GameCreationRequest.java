package com.backcasino.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameCreationRequest {

    private Integer playerId;
    private int betAmount;

}