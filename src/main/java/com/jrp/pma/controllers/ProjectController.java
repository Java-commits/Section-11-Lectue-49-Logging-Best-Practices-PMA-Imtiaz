package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;
	
	
	@Autowired
	EmployeeRepository empoRepo;
	
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		System.out.println(projects);
		model.addAttribute("projects", projects);
		return "/projects/list-projects";
		
	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = empoRepo.findAll();
		
		model.addAttribute("projects", aProject);
		model.addAttribute("allEmployees", employees);
		  
		return "/projects/new-projects";
		
	}
	
	@PostMapping("/save")
	 public String createProjectwithoutEmployees(Project aProject) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	   System.out.println("A project without employees");
	   proRepo.save(aProject);
	   return "redirect:/projects/new";
	 }

	
	
	 @PostMapping(path="/save", params= "employees")
	 public String createProject(Project aProject,  @RequestParam List<Long> employees, @RequestParam String stage) {
	 
	   proRepo.save(aProject);
	   
	   return "redirect:/projects";
	 }
	 
	
}
