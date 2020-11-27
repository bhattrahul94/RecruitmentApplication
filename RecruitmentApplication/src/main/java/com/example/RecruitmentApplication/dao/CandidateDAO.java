package com.example.RecruitmentApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RecruitmentApplication.entity.Candidate;

@Repository
public interface CandidateDAO extends JpaRepository<Candidate, Long> {

}
