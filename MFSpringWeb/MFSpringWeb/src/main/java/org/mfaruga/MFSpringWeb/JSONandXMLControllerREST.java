package org.mfaruga.MFSpringWeb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/third")
public class JSONandXMLControllerREST {

	
	public ModelAndView viewUser(@PathVariable("id") Long id) {
		return new ModelAndView("view").addObject("userid", id);
	}
	
	//@RequestMapping(path = "/new.json", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@RequestMapping(path = "/json/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public CreateTaskResponse createNewTaskJSON(@PathVariable("id") Long id) {
		Task task = new Task();
		task.setName(id.toString());
		task.setComments("Some comments");
		return new CreateTaskResponse(task);
	}

	//@RequestMapping(path = "/new.json", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@RequestMapping(path = "/xml/{id}", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public CreateTaskResponse createNewTaskXML(@PathVariable("id") Long id) {
		Task task = new Task();
		task.setName(id.toString());
		task.setComments("Some comments");
		return new CreateTaskResponse(task);
	}
}
