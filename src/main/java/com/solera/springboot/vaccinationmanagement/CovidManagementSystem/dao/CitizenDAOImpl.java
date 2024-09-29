package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity.Citizen;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.CitizenBadRequestException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.InvalidDateException;
import com.solera.springboot.vaccinationmanagement.CovidManagementSystem.rest.VaccineMismatchException;

@Repository
public class CitizenDAOImpl implements CitizenDAO{
	
	private EntityManager entityManager;
	
	public CitizenDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<Citizen> findPartlyVaccinated(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen where vaccination_status=:status", Citizen.class);
		theQuery.setParameter("status","Partly");
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	
	public List<Citizen> findFullyVaccinated(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen where vaccination_status=:status", Citizen.class);
		theQuery.setParameter("status","Fully");
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	
	public List<Citizen> findAll(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen", Citizen.class);
		
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	public List<Citizen> findCovaxinVaccinated(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen where vaccine_name=:status", Citizen.class);
		
		theQuery.setParameter("status","Covaxin");
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	public List<Citizen> findCovishieldVaccinated(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen where vaccine_name=:status", Citizen.class);
		
		theQuery.setParameter("status","Covishield");
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	public List<Citizen> findBoosterVaccinated(){
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Citizen> theQuery = currentSession.createQuery("from Citizen where vaccination_status=:status", Citizen.class);
		
		theQuery.setParameter("status","BoosterCompl");
		List<Citizen> citizens = theQuery.getResultList();
		return citizens;
	}
	public  HashMap<String,Integer> findCitizenVaccineCount(){
		Session currentSession = entityManager.unwrap(Session.class);
		List<Citizen> citizens=findAll();
		HashMap<String,Integer> hashVaccine=new HashMap<String,Integer>();
		for(Citizen citizen:citizens)
		{
			String vaccinationType=citizen.getVaccineName();
			if(!(vaccinationType==null))  
			{
				if(hashVaccine.get(vaccinationType)==null)
				{
					hashVaccine.put(vaccinationType,1);	
				}
				else
				{
					int count=hashVaccine.get(vaccinationType);
					hashVaccine.put(vaccinationType, ++count);	
				}
			}
		}
		return hashVaccine;
	}
	public  HashMap<String,Integer> findVaccineStatusCount(){
		Session currentSession = entityManager.unwrap(Session.class);
		List<Citizen> citizens=findAll();
		HashMap<String,Integer> hashStatus=new HashMap<String,Integer>();
		for(Citizen citizen:citizens)
		{
			String vaccinationStatus=citizen.getVaccinationStatus();
			
				if(hashStatus.get(vaccinationStatus)==null)
				{
					hashStatus.put(vaccinationStatus,1);	
				}
				else
				{
					int count=hashStatus.get(vaccinationStatus);
					hashStatus.put(vaccinationStatus, ++count);	
				}
		}
		return hashStatus;
	}
	
	public Citizen findById(int aadhaarNum) {
		Session currentSession = entityManager.unwrap(Session.class);
		Citizen theCitizen=currentSession.get(Citizen.class,aadhaarNum);
		
		return theCitizen;
	}
	
	public Citizen update(Citizen newCitizen) throws VaccineMismatchException, InvalidDateException, CitizenBadRequestException{
		Session currentSession = entityManager.unwrap(Session.class);
		
		Citizen oldCitizen=currentSession.get(Citizen.class,newCitizen.getAadhaarNumber());
		if(newCitizen.getVaccineName()!=null && oldCitizen.getVaccineName()!=null)
		{
			if(!((oldCitizen.getVaccineName()).equals(newCitizen.getVaccineName())))
			{
			throw new VaccineMismatchException("Both times same vaccine should be injected");
			}
		}
		if(newCitizen.getFirstVaccinationDate()!=null &&oldCitizen.getFirstVaccinationDate()==null)
		{
			if(newCitizen.getVaccineName()==null)
			{
				throw new CitizenBadRequestException("Vaccine name should be given");
			}
			if(newCitizen.getSecondVaccinationDate()!=null ||newCitizen.getBoosterDoseDate()!=null)
			{
				throw new CitizenBadRequestException("Two doses should not be taken at once");
			}
			
			oldCitizen.setFirstVaccinationDate(newCitizen.getFirstVaccinationDate());
			oldCitizen.setVaccinationStatus("Partly");
			oldCitizen.setVaccineName(newCitizen.getVaccineName());
			
		}
		else if(newCitizen.getSecondVaccinationDate()!=null&&oldCitizen.getSecondVaccinationDate()==null)
		{
			if(oldCitizen.getFirstVaccinationDate()==null)
			{
				throw new CitizenBadRequestException("Citizen haven't completed his first dose");
			}
			long timeDiff=(newCitizen.getSecondVaccinationDate().getTime())-(oldCitizen.getFirstVaccinationDate().getTime());
			long daysDiff=(timeDiff/(1000*60*60*24))%365;
			if(daysDiff<120)
			{
				throw new InvalidDateException("Gap between first dose and 2nd dose is less than 120 days");
				
			}
			oldCitizen.setSecondVaccinationDate(newCitizen.getSecondVaccinationDate());
			oldCitizen.setVaccinationStatus("Fully");
		}
		else if(newCitizen.getBoosterDoseDate()!=null&&oldCitizen.getBoosterDoseDate()==null)
		{
			if(oldCitizen.getFirstVaccinationDate()==null || oldCitizen.getSecondVaccinationDate()==null)
			{
				throw new CitizenBadRequestException("Citizen haven't done his 2 doses completely");
			}
			long timeDiff=(oldCitizen.getSecondVaccinationDate().getTime())-(newCitizen.getBoosterDoseDate().getTime());
			long daysDiff=(timeDiff/(1000*60*60*24))%365;
			if(daysDiff<270)
			{
				throw new InvalidDateException("Gap between 2nd dose and booster dose is less than 270 days");	
			}
			oldCitizen.setBoosterDoseDate(newCitizen.getBoosterDoseDate());
			oldCitizen.setVaccinationStatus("FullyPlusBooster");
		}
		currentSession.saveOrUpdate(oldCitizen);
		return oldCitizen;
		
	}
	public void deleteCitizen(int citizenAadhaarNum) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery=currentSession.createQuery("Delete from Citizen where aadhaarNumber=:citizenAadhaarNum");
		
		theQuery.setParameter("citizenAadhaarNum",citizenAadhaarNum);
		theQuery.executeUpdate();
	}

	@Override
	public Citizen add(Citizen theCitizen) throws CitizenBadRequestException {
		Session currentSession = entityManager.unwrap(Session.class);
		
		if(theCitizen.getAge()<18)
		{
			throw new CitizenBadRequestException("Age should be greater than or equal to 18 years");
		}
		theCitizen.setVaccinationStatus("Not Vaccinated");
		
		currentSession.save(theCitizen);
		return theCitizen;
	}

		

}
