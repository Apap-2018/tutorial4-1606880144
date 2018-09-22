/**
 * Nathanael Lemuella
 * 1606880144
 * APAP-C
 */

package com.example.demo.controller;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}

//	@RequestMapping("/challenge")
//	public String challenge(@RequestParam(value = "a") String name, Model model) {
//		model.addAttribute("name", name);
//		return "challenge";
//	}

	@RequestMapping(value = { "/challenge", "/challenge/{name}" })
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}

	@RequestMapping("/generator")
	public String generator(@RequestParam(value = "a", defaultValue = "0") int a,
			@RequestParam(value = "b", defaultValue = "0") int b, Model model) {
		model.addAttribute("a", a);
		model.addAttribute("b", b);

		if (a <= 1 && b <= 1) {
			model.addAttribute("text", "hm");
		} else {
			String teks = "h";
			String teksFinal = "";

			if (a < 1) {
				teks = "hm";
			} else {
				for (int i = 0; i < a; i++) {
					teks += "m";
				}
			}

			for (int j = 0; j < b; j++) {
				teksFinal += teks + " ";
			}
			model.addAttribute("text", teksFinal);
		}
		return "generator";
	}
}
