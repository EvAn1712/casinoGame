package com.backcasino.DAO;

import com.backcasino.models.BlackjackGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackjackDAO extends CrudRepository<BlackjackGame, Integer> {
}
