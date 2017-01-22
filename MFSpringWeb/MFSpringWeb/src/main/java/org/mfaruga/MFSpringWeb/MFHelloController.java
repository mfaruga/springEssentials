package org.mfaruga.MFSpringWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MFHelloController {

	@GetMapping("/")
	public String hello(Model model) {
		model.addAttribute("name", "Michal Faruga");
		return "welcome";
	}

}
