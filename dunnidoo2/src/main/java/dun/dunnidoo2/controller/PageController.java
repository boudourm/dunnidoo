package dun.dunnidoo2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dun.dunnidoo2.model.SEGMENT;


@Controller
public class PageController {
	


	
	@RequestMapping(value = { "/", "/home", "/index" , "/dashboard"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Dashboard");
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = "/charts")
	public ModelAndView charts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Charts");
		mv.addObject("userClickCharts", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		
		return mv;
	}	
	
	@RequestMapping(value = "/tables")
	public ModelAndView tables() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Tables");
		mv.addObject("userClickTables", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		return mv;
	}
	
	@RequestMapping(value = "/navbar")
	public ModelAndView navbar() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Navbar");
		mv.addObject("userClickNavbar", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	@RequestMapping(value = "/cards")
	public ModelAndView cards() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Cards");
		mv.addObject("userClickCards", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("title", "Login Page");
		mv.addObject("userClickLogin", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("title", "Registration Page");
		mv.addObject("userClickRegistration", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	@RequestMapping(value = "/forgot-password")
	public ModelAndView forgot() {
		ModelAndView mv = new ModelAndView("forgot-password");
		mv.addObject("title", "Forgot Password Page");
		mv.addObject("userClickForgot", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	@RequestMapping(value = "/blank")
	public ModelAndView blank() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Blank Page");
		mv.addObject("userClickBlank", true);
		
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		

		return mv;
	}
	
	
	@RequestMapping(value = "/segments/{libelle}/customers")
	public ModelAndView showSegmentCustomers(@PathVariable("libelle") String libelle	)
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", libelle);
		// to fetch a single segment
		SEGMENT segment = SEGMENT.get(libelle); 
		
		mv.addObject("title", segment.getLibelle());
	
		//passing the list of segments
		mv.addObject("segments" , SEGMENT.list()); 
		
		//passing the single segment object
		mv.addObject("segment" , segment);
		
		mv.addObject("userClickSegment", true);
		return mv;
	}
	
	
	
}
