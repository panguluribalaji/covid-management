package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity.Citizen;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.service.CitizenService;


@RestController
@RequestMapping("/Vaccination")
public class CitizenRestController {
		
	private CitizenService citizenService;
	public CitizenRestController(CitizenService theCitizenService) {
		 this.citizenService= theCitizenService;
	}
	
	@GetMapping("/CitizensPartly")
	public List<Citizen> findPartlyVaccinated(){
		
		return citizenService.findPartlyVaccinated();
	}
	
	@GetMapping("/CitizensFully")
	public List<Citizen> findFullyVaccinated(){
		
		return citizenService.findFullyVaccinated();
	}
	@GetMapping("/CitizensAllCompleted")
	public List<Citizen> findBoosterVaccinated(){
		
		return citizenService.findBoosterVaccinated();
	}  
	@GetMapping("/Citizens")
	public List<Citizen> findAll(){
		
		return citizenService.findAll();
	}
	
	@GetMapping("/CitizensCovaxin")
	public List<Citizen> findCovaxinVaccinated(){
		
		return citizenService.findCovaxinVaccinated();
	}
	@GetMapping("/CitizensCovishield")
	public List<Citizen> findCovishieldVaccinated(){
		
		return citizenService.findCovishieldVaccinated();
	}
	@GetMapping("/CitizensVaccineCountWiseReport")
	public  HashMap<String,Integer> findCitizenVaccineCount(){
		
		return citizenService.findCitizenVaccineCount();
	}
	@GetMapping("/VaccineStatusCountWiseReport")
	public  HashMap<String,Integer> findVaccineStatusCount(){
		
		return citizenService.findVaccineStatusCount();
	}
	
	@GetMapping("/Citizens/{strCitizenAadhaarNum}")
	public Citizen getEmployee(@PathVariable String strCitizenAadhaarNum) throws Exception {
		if(!Pattern.matches("[0-9]+",strCitizenAadhaarNum))
		{
			throw new CitizenBadRequestException("Given aadhaarNum is not an integer");
		}
		int citizenAadhaarNum=Integer.parseInt(strCitizenAadhaarNum);
		Citizen theCitizen=citizenService.findById(citizenAadhaarNum);
		if(theCitizen==null)
		{
			throw new CitizenNotFoundException("Citizen not found ");
			
		}
		else if(!(theCitizen.getVaccinationStatus().equals("FullyPlusBooster")))
		{
			throw new Exception("Citizen is not fully Vaccinated So getting citizen information is not possible");
		}
		else 
		{
			return theCitizen;
		}
	}
	@PostMapping("/CitizenReg")
	public Citizen addCitizen(@RequestBody Citizen theCitizen) throws Exception
	{
		citizenService.add(theCitizen);
		return theCitizen ;
	}
	
	@PutMapping("/CitizenUpdate")
	public Citizen updateCitizen(@RequestBody Citizen newCitizen) throws VaccineMismatchException, InvalidDateException, CitizenBadRequestException 
	{
		Citizen theCitizen=citizenService.update(newCitizen);	
		return theCitizen;
				
	}
	
	@DeleteMapping("/CitizenDelete/{citizenAadhaarNum}")
	
	public String deleteCitizen(@PathVariable int citizenAadhaarNum) throws Exception {
		Citizen tempCitizen=citizenService.findById(citizenAadhaarNum);
		
		if(tempCitizen==null)
		{
			return "Citizen not found ";
		}
		else if(!(tempCitizen.getVaccinationStatus().equals("FullyPlusBooster")))
		{
			throw new Exception("Citizen is not fully vaccinated so deletion of member is illegal");
		}
		
		citizenService.deleteCitizen(citizenAadhaarNum);
		return "Deleted Citizen ";
		
	}
	
	

}
