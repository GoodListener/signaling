package com.ek.signaling.repository;

import com.ek.signaling.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
}
