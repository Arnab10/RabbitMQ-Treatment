package com.atmos.oxygen.dto;

import lombok.Data;

@Data

public class HireStatus {
	private Candidate candidate;
	private boolean hired;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public boolean isHired() {
		return hired;
	}

	public void setHired(boolean hired) {
		this.hired = hired;
	}

	@Override
	public String toString() {
		return "HireStatus [candidate=" + candidate + ", hired=" + hired + "]";
	}

	public HireStatus(Candidate candidate, boolean hired) {
		super();
		this.candidate = candidate;
		this.hired = hired;
	}

	public HireStatus() {
		super();
	}

}
