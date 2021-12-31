package com.atmos.oxygen.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Candidate {

	@Id
	private String applicantId;
	private String name;
	private String email;
	private String metric;
	private String experience;
	private String designation;
	private String company;

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Candidate(String applicantId, String name, String email, String metric, String experience,
			String designation, String company) {
		super();
		this.applicantId = applicantId;
		this.name = name;
		this.email = email;
		this.metric = metric;
		this.experience = experience;
		this.designation = designation;
		this.company = company;
	}

	@Override
	public String toString() {
		return "Candidate [applicantId=" + applicantId + ", name=" + name + ", email=" + email + ", metric=" + metric
				+ ", experience=" + experience + ", designation=" + designation + ", company=" + company + "]";
	}

	public Candidate() {
		super();
	}

}
