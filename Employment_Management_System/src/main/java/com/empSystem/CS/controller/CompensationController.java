package com.empSystem.CS.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empSystem.CS.entity.CompensationEntity;
import com.empSystem.CS.service.CompensationService;
import com.empSystem.CS.service.EmployeeService;

@Controller
public class CompensationController {

	private CompensationService compensationService;
	private EmployeeService employeeService;

	public CompensationController(CompensationService compensationService, EmployeeService employeeService) {
		super();
		this.compensationService = compensationService;
		this.employeeService = employeeService;
	}

	@GetMapping("/employee/compensation/{id}")
	public String listcompensations(@PathVariable Long id, Model model) {

		model.addAttribute("employee", id);
		model.addAttribute("compensation", compensationService.getCompensationHistory(id));

		return "compensation_history";

	}

	@GetMapping("/employee/compensation/new")
	public String createEmployeeCompensationForm(Model model) {
		CompensationEntity compensationEntity = new CompensationEntity();

		model.addAttribute("employeeList", employeeService.getAllEmployee());
		model.addAttribute("compensation", compensationEntity);

		List<String> typeList = Arrays.asList("Salary", "Bonus", "Commission", "Allowance", "Adjustment");
		model.addAttribute("typeList", typeList);

		return "create_compensation";
	}

	@PostMapping("/employee/compensation/save")
	public String saveEmployeeCompensation(@ModelAttribute("compensation") CompensationEntity compensationEntity,
			RedirectAttributes redirAttrs) {
		String type = compensationEntity.getType();
		Long check = checkEmployeeSalary(compensationEntity.getCompensationDate(), compensationEntity.getEmployeeId());
		if (check > 0 && type.equals("Salary")) {
			redirAttrs.addFlashAttribute("duplicate", "Employee salary already exists for this month...");
			return "redirect:/employee/compensation/new";
		} else {
			compensationService.saveCompensation(compensationEntity);

			redirAttrs.addFlashAttribute("added", "New compensation has been added successfully...");

			return "redirect:/employee";
		}
	}

	@GetMapping("/employee/compensation/breakdown/{employeeId}/{compensationDate}")
	public String getCompensationBreakdown(@PathVariable("employeeId") Long employeeId,
			@PathVariable("compensationDate") Date date, Model model) {
		model.addAttribute("date", date);
		model.addAttribute("employee", employeeId);
		model.addAttribute("compensation", compensationService.getCompensationBreakdown(date, employeeId));
		return "compensation";
	}

	@GetMapping("/employee/compensation/edit/{employeeId}/{compensationId}")
	public String editCompensationForm(@PathVariable("employeeId") Long employeeId,@PathVariable("compensationId") Long CompnID, Model model) {
		model.addAttribute("employee", employeeId);	
		model.addAttribute("compensation", compensationService.getCompensationById(CompnID));
		return "edit_compensation";
	}

	@PostMapping("/employee/compensation/update/{employeeId}/{compensationId}")
	public String updateEmployeeCompensation(@PathVariable("employeeId") Long employeeId,
			@PathVariable("compensationId") Long compensationId, Model model,
			@ModelAttribute("compensation") CompensationEntity compensationEntity, RedirectAttributes redirAttrs) {
		CompensationEntity existingCompensation = compensationService.getCompensationById(compensationId);
		existingCompensation.setCompnID(compensationId);
		existingCompensation.setAmount(compensationEntity.getAmount());
		existingCompensation.setDescrpt(compensationEntity.getDescrpt());
		existingCompensation.setDate(compensationEntity.getDate());
		existingCompensation.setType(compensationEntity.getType());
		existingCompensation.setEmployeeId(compensationEntity.getEmployeeId());
		redirAttrs.addFlashAttribute("updated", "Employee compensation details has been updated successfully...");
		compensationService.saveCompensation(existingCompensation);
		return "redirect:/employee/compensation/{employeeId}";
	}

	public Long checkEmployeeSalary(Date date, Long employeeId) {
		Long salary = compensationService.checkEmployeeSalary(date, employeeId);
		if (salary == null) {
			salary = (long) 0;
		}
		return salary;
	}

}
