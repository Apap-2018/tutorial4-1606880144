package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}

	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

		model.addAttribute("pilot", archive);
		return "view-pilot";
	}

	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getListPilot();

		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}

	@RequestMapping(value = { "/pilot/view/license-number/{licenseNumber}" })
	public String viewLicense(@PathVariable("licenseNumber") String liNum, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(liNum);
		if (archive == null) {
			model.addAttribute("idFound", false);
		} else {
			model.addAttribute("idFound", true);
			model.addAttribute("pilot", archive);
		}
		return "viewpath-pilot";
	}

	@RequestMapping(value = { "/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}" })

	public String updateFlyHours(@PathVariable("licenseNumber") String liNum, @PathVariable("flyHour") int flHour,
			Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(liNum);
		if (archive == null) {
			model.addAttribute("idFound", false);
		} else {
			model.addAttribute("idFound", true);
			archive.setFlyHour(flHour);
			model.addAttribute("pilot", archive);
		}
		return "update";
	}

	@RequestMapping(value = { "/pilot/delete/id/{id}" })
	public String deleteID(@PathVariable("id") String id, Model model) {
		PilotModel pilot = null;
		List<PilotModel> daftarPilot = pilotService.getListPilot();

		for (int i = 0; i < daftarPilot.size(); i++) {
			PilotModel iterasiPilot = daftarPilot.get(i);
			if (iterasiPilot.getId().equals(id)) {
				pilot = iterasiPilot;
				daftarPilot.remove(i);
				break;
			}
		}
		if (pilot == null) {
			model.addAttribute("idFound", false);
		} else {
			model.addAttribute("idFound", true);

		}
		return "delete-pilot";
	}
}
