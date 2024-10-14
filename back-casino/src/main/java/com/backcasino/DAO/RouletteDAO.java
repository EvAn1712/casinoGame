package com.backcasino.DAO;

import com.backcasino.models.RouletteGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouletteDAO extends CrudRepository<RouletteGame, Integer> {
}
