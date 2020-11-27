package com.example.RecruitmentApplication.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecruitmentApplication.dao.JobDAO;
import com.example.RecruitmentApplication.entity.Job;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDAO repo;
	
	@Override
	public List<Job> listAll(){
		
		return repo.findAll();
	}
	
	@Override
	public void save(Job job) {
		repo.save(job);
	}
	
	@Override
	public Job get(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
