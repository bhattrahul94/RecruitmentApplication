 package com.example.RecruitmentApplication.Service;

 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.RecruitmentApplication.beans.CandidateDTO;
import com.example.RecruitmentApplication.dao.CandidateDAO;
import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;
import com.example.RecruitmentApplication.utility.ApplicationStatus;


@ExtendWith(MockitoExtension.class)
@TestInstance(value = Lifecycle.PER_CLASS)
class CandidateServiceImplTest {
	
	@Autowired 
	MockMvc mvc;
	
	@Mock
	private CandidateDAO candidateDAO;
	
	@InjectMocks
	private CandidateServiceImpl candidateService;
	
	@Mock
	private JobServiceImpl jobService;
	
	private Candidate candidate1;
	
	private Candidate candidate2;
	
	private CandidateDTO candidate3;
	
	private List<Candidate> candidateList;
	
	private Job job;
	
	
	@BeforeAll()
	void setup() {
		job = new Job(1L, "freak", new Date(), 0);
		System.out.println(job.getTitle());
		
		//Long id, String name, String phoneno, String email, String resume, Job job,
		//ApplicationStatus application_status
		candidateList = new ArrayList<Candidate>();
		candidate1 = new Candidate(1L,"rahul","123","a@b.com","qerty",job, ApplicationStatus.APPLIED);
		candidate2 = new Candidate(2L,"ravi","129","q@p.com","pofy",job, ApplicationStatus.APPLIED);
		candidateList.add(candidate1);
		candidateList.add(candidate2);
		//String email, String phoneno, String resume, Long job_id
		candidate3 = new CandidateDTO("rahul","a@b.com","123","asdfgh",1L); 

	}

	@Test
	void testListAll() throws SQLException {
		Mockito.when(candidateDAO.findAll()).thenReturn(candidateList);
		
		List<Candidate> listOfCandidates = candidateService.listAll();
		
		Assertions.assertNotNull(listOfCandidates);
		Assertions.assertEquals(2, listOfCandidates.size());
	}
	
	

	@Test
	void testGetCandidateById() {
		Optional<Candidate> candidate = Optional.of(candidateList.get(0));
		Long id = 1L;
		Mockito.when(candidateDAO.findById(id)).thenReturn(candidate);	
		
		Candidate requiredCandidate = candidateService.getCandidateById(id);
		
		Assertions.assertNotNull(requiredCandidate);
		Assertions.assertEquals(candidate.get(), requiredCandidate);
	}


}
