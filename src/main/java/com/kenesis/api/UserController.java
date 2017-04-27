package com.kenesis.api;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody String createUser(UserVO vo) {
		logger.info("createUser");
		service.signup(vo);
		return "success";
	}
	
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("userid")String userid) {
		logger.info("deleteUser");
		service.signout(userid);
		return "success";
	}
	
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public @ResponseBody UserVO readUser(@PathVariable("userid")String userid) {		
		UserVO vo = (UserVO) service.read(userid);
		logger.info("showUserInfo : ",vo );
		return vo;
	}
	
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.POST)
	public @ResponseBody String updateUser(@PathVariable("userid")String userid, @RequestParam("userpw")String userpw, @RequestParam("homelocation")String homelocation ) {
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
