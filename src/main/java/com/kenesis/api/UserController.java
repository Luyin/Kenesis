package com.kenesis.api;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenesis.domain.UserVO;
import com.kenesis.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/v1")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService service;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("userid")String userid) {
		logger.info("deleteUser");
		service.signout(userid);
		return "success";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		logger.info("deleteUser");
		service.signout(userid);
		return "success";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public @ResponseBody UserVO readUser(@PathVariable("userid")String userid) {	
		UserVO vo = (UserVO) service.read(userid);
		logger.info("showUserInfo : ",userid );
		return vo;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody UserVO readUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		UserVO vo = (UserVO) service.read(userid);
		logger.info("showUserInfo : ",userid );
		return vo;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody String updateUser(@RequestParam("userid")String userid, @RequestParam("userpw")String userpw, @RequestParam("homelocation")String homelocation ) {
		logger.info("updateUser");
		
		UserVO vo = (UserVO) service.read(userid);
		
		if(userpw != null)
		{
			vo.setUserpw(userpw);
		}
		
		if(homelocation != null)
		{
			vo.setHomelocation(homelocation);
		}
		
		logger.info(vo.toString());
		
		service.update(vo);
		
		return "success";
	}
	
}
