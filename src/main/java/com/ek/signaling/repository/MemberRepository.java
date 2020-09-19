package com.ek.signaling.repository;

import com.ek.signaling.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
    List<Member> findByTeamId(String teamId);
}
