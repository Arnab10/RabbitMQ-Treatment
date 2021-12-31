package com.atmos.oxygen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atmos.oxygen.dto.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String> {

}
