package com.tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class LoginController {

	@PostMapping("/login")
	public String userLogin(@RequestParam String username,
			@RequestParam String password,
			RedirectAttributes redirectAttributes) {
		
		if (username.equals("admin") && password.equals("admin")) {
			return "redirect:/dashboard";
		}else {
			redirectAttributes.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}
		
	}

}
