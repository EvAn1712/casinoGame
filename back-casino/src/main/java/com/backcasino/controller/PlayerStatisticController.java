package com.backcasino.controller;

import com.backcasino.models.Performance;
import com.backcasino.services.PlayerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/playerStatistics")
public class PlayerStatisticController {

    @Autowired
    private PlayerStatisticService playerStatisticService;

    @GetMapping("/{playerId}")
    public ResponseEntity<Performance> getPlayerStatistics(@PathVariable Integer playerId) {
        Performance performance = playerStatisticService.findByPlayerId(playerId);
        return performance != null ? ResponseEntity.ok(performance) : ResponseEntity.notFound().build();
    }
}
