package com.scauzgq.redis_mysql_02.controllers;

import com.scauzgq.redis_mysql_02.beans.User;
import com.scauzgq.redis_mysql_02.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Scope("prototype")
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private User user;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/selectUserByUserName",method= RequestMethod.POST)
	public ModelAndView selectUserByUserName(@RequestParam(value="userName") String userName) {
		ModelAndView mv;
		System.out.println("validating username..." + userName);
		user = userService.selectUserByUserName(userName);
		mv = new ModelAndView("/success");
		mv.addObject("user", user);
		return mv;

	}

	@RequestMapping(value="/updateUser",method= RequestMethod.POST)
	public ModelAndView updateUser(User user) {
		ModelAndView mv;
		System.out.println("update user...");
		userService.updateUser(user);
		mv = new ModelAndView("/success");
		mv.addObject("user", user);
		return mv;

	}
}
