package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.dao.CitizenDAO;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity.Citizen;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.CitizenBadRequestException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.InvalidDateException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.VaccineMismatchException;



@Service
public class CitizenServiceImpl implements CitizenService{
	
	private CitizenDAO citizenDAO;
	public CitizenServiceImpl(CitizenDAO theCitizenDAO) {
		this.citizenDAO = theCitizenDAO;
	}

	
	public List<Citizen> findPartlyVaccinated() {
		
		return citizenDAO.findPartlyVaccinated();
	}
	
	public List<Citizen> findFullyVaccinated() {
		
		return citizenDAO.findFullyVaccinated();
	}
	public List<Citizen> findAll() {
		
		return citizenDAO.findAll();
	}
	public List<Citizen> findCovishieldVaccinated() {
		
		return citizenDAO.findCovishieldVaccinated() ;
	}
	public List<Citizen> findCovaxinVaccinated() {
		return citizenDAO.findCovaxinVaccinated();
	}
	public List<Citizen> findBoosterVaccinated() {
		return citizenDAO.findBoosterVaccinated();
	}
	public  HashMap<String,Integer> findCitizenVaccineCount(){
		return citizenDAO.findCitizenVaccineCount();
	}
	public HashMap<String,Integer> findVaccineStatusCount(){
		return citizenDAO.findVaccineStatusCount();
	}
	
	
	public Citizen findById(int citizenAadhaarNum){
		
		return citizenDAO.findById(citizenAadhaarNum);
	}
	@Override
	@Transactional
	public Citizen add(Citizen theCitizen) throws CitizenBadRequestException {
		// TODO Auto-generated method stub
		return citizenDAO.add(theCitizen);
	}
	
	
	@Transactional
	public Citizen update(Citizen theCitizen) throws VaccineMismatchException, InvalidDateException, CitizenBadRequestException{
		 return citizenDAO.update(theCitizen);
	}
	
	@Transactional
	public void deleteCitizen(int citizenAadhaarNum) {
		
		citizenDAO.deleteCitizen(citizenAadhaarNum);		
	}


	


	
	
	
	
}
