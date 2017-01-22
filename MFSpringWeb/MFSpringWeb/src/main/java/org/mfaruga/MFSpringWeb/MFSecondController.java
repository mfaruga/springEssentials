package org.mfaruga.MFSpringWeb;

import java.io.IOException;
import java.util.Random;

import javax.validation.Valid;

import org.mfaruga.MFSpringWeb.security.MFAuthorities;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/second")
public class MFSecondController {

	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidator());
	}

	@ModelAttribute(value = "textFromFunction")
	public String textFromFunction() {
		return "Some text from function";
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	// TODO Do the same for methods in service layer and also for domain objects 
	// @PreAuthorize("@businessServiceImpl.isEligibleToSeeUserData
	// (principal, #username)")
	// @RequestMapping("/userdata/{username}")
	// public String getUserPage(@PathVariable String username,ModelMap
	// model) {
	// {...}
	public String hello(Model model) {
		model.addAttribute("name", "Second Controller: Michal Faruga");
		String[] users = new String[] { "Michal", "Sylwia", "Bartus", "Milenka" };
		model.addAttribute("users", users);
		model.addAttribute("usersCount", users.length);
		return "welcome";
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public String listAll(Model model) {
		model.addAttribute("name", "Second Controller: Michal Faruga");
		String[] users = new String[] { "Michal", "Sylwia", "Bartus", "Milenka" };
		model.addAttribute("users", users);
		model.addAttribute("usersCount", users.length);
		return "list";
	}

	@RequestMapping(path = "/view", method = RequestMethod.GET)
	public String viewUser(Model model) {
		return "view";
	}

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String newUserForm(Model model) {
		Random rand = new Random();
		int userId = rand.nextInt(1000);
		User user = new User();
		user.setId(new Long(userId));
		user.setName("mfaruga");
		user.setUserName("user name");
		model.addAttribute("user", user);
		return "new";
	}

	@RequestMapping(path = "/new", method = RequestMethod.POST)
	public String saveNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

		System.out.println("Created user: " + user.toString());

		if (result.hasErrors()) {
			System.out.println("There are errors!");
			// return new ModelAndView("new").addObject("user",user);
			return "new";
		}
		System.out.println("No errors!");

		return "view";
		// ModelAndView modelAndView = new ModelAndView("view");
		// modelAndView.addObject("user",user);
		// return modelAndView;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewUser(@PathVariable("id") Long id) {
		return new ModelAndView("view").addObject("userid", id);
	}

	@RequestMapping(path = "/{userId}/profileForm", method = RequestMethod.POST)
	public String uploadProfileImage(@PathVariable("userId") Long id, @RequestParam("profileImage") MultipartFile file)
			throws IOException {
		String rootDir = "/home/michal/TEMP/servlet-uploads" + "/" + id;
		if (!file.isEmpty()) {
			java.io.File fileDir = new java.io.File(rootDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			String targetFile = rootDir + "/" + file.getOriginalFilename();
			FileCopyUtils.copy(file.getBytes(), new java.io.File(targetFile));
			System.out.println("File uploaded: " + targetFile);
		}
		System.out.println("File upload finished"); 
		return "view";
	}

}
