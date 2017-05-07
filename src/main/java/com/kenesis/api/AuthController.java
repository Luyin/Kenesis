package com.kenesis.api;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenesis.domain.UserVO;
import com.kenesis.security.JwtUtils;
import com.kenesis.security.domain.AutehnticationRequest;
import com.kenesis.security.domain.AuthenticationResponse;
import com.kenesis.service.UserService;
import com.nimbusds.jose.JOSEException;


@Controller
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Inject
	private UserService userService;
	
	@Inject
	private AuthenticationManager authenticationManager;

	
	@RequestMapping(value = "/auth", method = RequestMethod.POST )
    public ResponseEntity<?> authenticationRequest(@RequestBody AutehnticationRequest authenticationRequest) throws AuthenticationException, IOException, JOSEException{

		logger.info("/auth");
		
        String userid = authenticationRequest.getUserid();
        String password = authenticationRequest.getPassword();
        
        UserVO vo = userService.read(userid);
        
        if(!password.equals(vo.getUserpw()))
        {
        	new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        String secret = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("secret.key"), Charset.defaultCharset().displayName());
        int expirationInMinutes = 24*60;
        
        String token = JwtUtils.generateHMACToken(userid, "ROLE_USER", secret, expirationInMinutes);

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
	
	@RequestMapping(value = "/auth/register", method = RequestMethod.POST)
	public @ResponseBody String createUser(UserVO vo) {
		logger.info("createUser");
		userService.signup(vo);
		return "success";
	}
}
