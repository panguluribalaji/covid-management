package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.dao;

import java.util.HashMap;
import java.util.List;

import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity.Citizen;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.CitizenBadRequestException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.InvalidDateException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.VaccineMismatchException;

public interface CitizenDAO {
	
	public List<Citizen> findPartlyVaccinated();
	
	public List<Citizen> findFullyVaccinated();
	public List<Citizen>findAll();
	public List<Citizen> findCovishieldVaccinated();
	public List<Citizen> findCovaxinVaccinated();
	public List<Citizen> findBoosterVaccinated();
	public  HashMap<String,Integer> findCitizenVaccineCount();
	public  HashMap<String,Integer> findVaccineStatusCount();
	public Citizen findById(int citizenAadhaarNum);
	public Citizen add(Citizen theCitizen) throws CitizenBadRequestException;
	public Citizen update(Citizen theCitizen) throws VaccineMismatchException, InvalidDateException, CitizenBadRequestException;
    public void deleteCitizen(int citizenAadhaarNum);

}
