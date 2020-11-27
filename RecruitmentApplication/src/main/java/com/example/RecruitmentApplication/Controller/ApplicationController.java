package com.example.RecruitmentApplication.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationController {
	
	
	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> index(){
		return ResponseEntity.ok("Index Page Of Application");
	}
	
	
	
	
	
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Job> read(@PathVariable("id") Long id) {
//	    Job foundJob = jobservice.get(id);
//	    if (foundJob == null) {
//	        return ResponseEntity.notFound().build();
//	    } else {
//	        return ResponseEntity.ok(foundJob);
//	    }
//	}
	
	
//	@RequestMapping("/Recruiter")
//	public String recruiterHomePage(Model model) {
//		
//		List<Candidate> listCandidates = service.listAll();
//		List<Job> listOfJobs = jobservice.listAll();
//		
//		System.out.println("JOBS===============>>>>>>>>>>>.");
//		listOfJobs.forEach(System.out :: print);
//		
//		model.addAttribute("listCandidates", listCandidates);
//		
//		model.addAttribute("listOfJobs", listOfJobs);
//		
//		return "recruiter_home";
//	}
//	
//	
//	@RequestMapping("/new")
//	public String newJobForm(Model model) {
//		Job job = new Job();
//		model.addAttribute("job", job);
//		return "new_job";
//	}
//	
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public String createJob(@ModelAttribute("job") Job job) {
//		
//		jobservice.save(job);
//		return "redirect:/";
//	}
//	
//	
//	@RequestMapping(value = "/submit", method = RequestMethod.POST)
//	public String submitApplication(@ModelAttribute("candidate") Candidate candidate) {
//		service.createJobApplication(candidate);
//		return "redirect:/";
//	}
//	
//	
//	
//	// Have to work on this
//	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
//	public String findApplicantsByJobId(@PathVariable(name = "id") Long id, Model model) {
//		List<Candidate> listCandidates = service.getAllCandidatesByJobId(id);
//		model.addAttribute("listCandidates", listCandidates);
//
//		return "job_applicants";
//	}
//	
//
//	@RequestMapping(value = "/apply", method = RequestMethod.GET)
//	public String applyForJob(Model model) {
//		
//		List<Job> listOfJobs = jobservice.listAll();
//		Candidate candidate = new Candidate();
//		model.addAttribute("listOfJobs", listOfJobs);
//		
//		model.addAttribute("candidate", candidate);
//		
//		return "job_application_form";
//		
//	}
//	
//	@InitBinder
//	private void dateBinder(WebDataBinder binder) {
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    
//	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//	 
//	    binder.registerCustomEditor(Date.class, editor);
//	}

}
