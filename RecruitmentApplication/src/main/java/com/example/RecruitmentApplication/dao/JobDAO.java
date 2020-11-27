package com.example.RecruitmentApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.RecruitmentApplication.entity.Job;

@Repository
public interface JobDAO extends JpaRepository<Job, Long>{
	
	@Modifying
    @Query(value = "update Job set numberofapplications = numberofapplications + 1 WHERE id = :jobId")
    public void increaseNumOfApplication(@Param("jobId") Long jobId);
	
	


}
