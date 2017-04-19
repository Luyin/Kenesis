package com.kenesis.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/v1")
public class EncodingController {
	private static final Logger logger = LoggerFactory.getLogger(EncodingController.class);

	@RequestMapping(value = "/encoding/{userid}", method = RequestMethod.POST )
	public void EncodeVideo()
	{
		
	}
}
