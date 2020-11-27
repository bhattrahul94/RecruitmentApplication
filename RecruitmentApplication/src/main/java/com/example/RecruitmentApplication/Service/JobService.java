package com.example.RecruitmentApplication.Service;

import java.util.List;

import com.example.RecruitmentApplication.entity.Job;

public interface JobService {
	
	public List<Job> listAll();
	
	public void save(Job job);
	
	public Job get(Long id);
	
	public void delete(Long id);

}
