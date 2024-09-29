package com.solera.springboot.vaccinationmanagement.CovidManagementSystem.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity(name="Citizen")
@Table(name="Citizen")
@SecondaryTable(name = "Vaccine", pkJoinColumns = @PrimaryKeyJoinColumn(name = "AADHAAR_NUMBER"))
 public class Citizen {
	
	@Id
	@Column(name="aadhaar_number")
	private int aadhaarNumber;
	/*@SequenceGenerator(name="SEQ_GEN",sequenceName="id_seq3",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")*/
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="AGE")
	private int age;
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="FIRST_VACCINATION_DATE",table = "Vaccine")
	private Date firstVaccinationDate;
	@Column(name="SECOND_VACCINATION_DATE",table = "Vaccine")
	private Date secondVaccinationDate;
	@Column(name="BOOSTER_DOSE_DATE",table = "Vaccine")
	private Date boosterDoseDate;
	@Column(name="VACCINE_NAME",table = "Vaccine")
	private String vaccineName;
	@Column(name="VACCINATION_STATUS",table = "Vaccine")
	private String vaccinationStatus;
	
	public Citizen(int aadhaarnumber, int id, String name, int age, String gender, Date firstVaccinationDate,
			Date secondVaccinationDate, Date boosterDoseDate, String vaccineName, String vaccinationStatus) {
		this.aadhaarNumber = aadhaarnumber;
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.firstVaccinationDate = firstVaccinationDate;
		this.secondVaccinationDate = secondVaccinationDate;
		this.boosterDoseDate = boosterDoseDate;
		this.vaccineName = vaccineName;
		this.vaccinationStatus = vaccinationStatus;
	}

	public Citizen() {
	}
	
	@Override
	public String toString() {
		return "Citizen [aadharnumber=" + aadhaarNumber + ", id=" + id + ", name=" + name + ", age=" + age + ", gender="
				+ gender + ", firstVaccinationDate=" + firstVaccinationDate + ", secondVaccinationDate="
				+ secondVaccinationDate + ", boosterDoseDate=" + boosterDoseDate + ", vaccineName=" + vaccineName
				+ ", vaccinationStatus=" + vaccinationStatus + "]";
	}

	public int getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(int aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getFirstVaccinationDate() {
		return firstVaccinationDate;
	}

	public void setFirstVaccinationDate(Date firstVaccinationDate) {
		this.firstVaccinationDate = firstVaccinationDate;
	}

	public Date getSecondVaccinationDate() {
		return secondVaccinationDate;
	}

	public void setSecondVaccinationDate(Date secondVaccinationDate) {
		this.secondVaccinationDate = secondVaccinationDate;
	}

	public Date getBoosterDoseDate() {
		return boosterDoseDate;
	}

	public void setBoosterDoseDate(Date boosterDoseDate) {
		this.boosterDoseDate = boosterDoseDate;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccinationStatus() {
		return vaccinationStatus;
	}

	public void setVaccinationStatus(String vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}

	
	
	
	
	
	
}

	