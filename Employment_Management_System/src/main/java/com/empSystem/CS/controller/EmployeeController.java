package com.empSystem.CS.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empSystem.CS.entity.EmployeeEntity;
import com.empSystem.CS.repository.EmployeeRepository;
import com.empSystem.CS.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeRepository employeeRepo;
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepo) {
		super();
		this.employeeService = employeeService;
		this.employeeRepo = employeeRepo;
	}

	@GetMapping("/employee")
	public String listEmployee(Model model) {
		model.addAttribute("employee", employeeService.getAllEmployee());
		return "employee";
	}

	@GetMapping("/employee/new")
	public String createEmployeeForm(Model model) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		model.addAttribute("employeeEntity", employeeEntity);
		return "create_Employee";
	}

	@PostMapping("/employee")
	public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employeeEntity,
			RedirectAttributes redirAttrs) {

		Long check = duplicateEmployee(employeeEntity.getFname(), employeeEntity.getLname(), employeeEntity.getMname(),
				employeeEntity.getBdate());
		if (check > 0) {
			redirAttrs.addFlashAttribute("duplicate", " Employee already exists!");
			return "redirect:/employee/new";
		} else {
			employeeService.saveEmployee(employeeEntity);
			redirAttrs.addFlashAttribute("added", " Employee added successfully!");
			return "redirect:/employee";
		}

	}

	// search

	@PostMapping("/employee/search")
	public String searchEmployees(Model model, @ModelAttribute("filter") EmployeeEntity filter) {
		model.addAttribute("employee",
				employeeService.searchEmployees(filter.getFname(), filter.getLname(), filter.getPosition()));
		model.addAttribute("Fname", filter.getFname());
		model.addAttribute("Lname", filter.getLname());
		model.addAttribute("Position", filter.getPosition());
		return "employee";
	}

	// edit

	@GetMapping("/employee/edit/{id}")
	public String editEmployeeForm(@PathVariable(name = "id") Long id, Model model) {
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(id);
		model.addAttribute("employeeEntity", employeeEntity);
		return "edit_employeeEntity";
	}

	@PostMapping("/employee/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") EmployeeEntity employeeEntity,
			BindingResult result, RedirectAttributes attribute) {

		if (result.hasErrors()) {
			return "addEmployee";
		}

		Long check = duplicateEmployee(employeeEntity.getFname(), employeeEntity.getLname(), employeeEntity.getMname(),
				employeeEntity.getBdate());
		if (check == employeeEntity.getId() || check == 0) {

			EmployeeEntity existingEmployee = employeeService.getEmployeeById(id);
			existingEmployee.setId(id);
			existingEmployee.setFname(employeeEntity.getFname());
			existingEmployee.setMname(employeeEntity.getMname());
			existingEmployee.setLname(employeeEntity.getLname());
			existingEmployee.setBdate(employeeEntity.getBdate());
			existingEmployee.setPosition(employeeEntity.getPosition());

			// save
			employeeService.updateEmployee(existingEmployee);
			attribute.addFlashAttribute("updated", " Employee updated successfully!");
			return "redirect:/employee";
		} else {
			attribute.addFlashAttribute("duplicate", " Employee already exists!");
			return "redirect:/employees/edit/{id}";
		}
	}

	public Long duplicateEmployee(String firstName, String lastName, String middleName, Date birthDate) {
		Long duplicate = employeeService.searchDuplicate(firstName, lastName, middleName, birthDate);
		if (duplicate == null) {
			duplicate = (long) 0;
		}
		return duplicate;
	}

}
