package com.kenesis.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/v1")
public class EncodeController {
	private static final Logger logger = LoggerFactory.getLogger(EncodeController.class);

	@RequestMapping(value = "/encode", method = RequestMethod.POST )
	public void EncodeVideo(@RequestParam("location")String location)
	{
		
	}
	
	@RequestMapping(value = "/encode", method = RequestMethod.DELETE )
	public void CancelEncode(@RequestParam("location")String location)
	{
		
	}
	
	@RequestMapping(value = "/encode/", method = RequestMethod.GET)
	public void EncodeProgress()
	{
		
	}
}
