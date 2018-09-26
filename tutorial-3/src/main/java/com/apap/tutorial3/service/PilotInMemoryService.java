package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.PilotModel;

import org.springframework.stereotype.Service;

/**
 * PilotInMemoryService
 * 
 * @author LOUIS
 *
 */

@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;

	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}

	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}

	@Override
	public List<PilotModel> getListPilot() {
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		for (PilotModel p : archivePilot) {
			if (p.getLicenseNumber().equals(licenseNumber)) {
				return p;
			}
		}
		return null;
	}

}
