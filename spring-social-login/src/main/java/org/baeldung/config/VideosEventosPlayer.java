package org.baeldung.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Secured("FACEBOOK_USER")
@RequestMapping("/PlayerVideo")
@Controller

public class VideosEventosPlayer {
	String videoSrc = "/video/streaming1";
	String type="video/ogg"; 
	
	
	@Autowired
    private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	
	//@RequestMapping("/")
	public ModelAndView welcome(Model model, Principal principal/*, WebRequest request*/) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//User user = (User) authentication.getPrincipal();
		//user.getUsername();
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //UserDetails userDetail = (UserDetails) auth.getPrincipal();
		//LoggedUser principal = (LoggedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Usuario user = principal.getLoggedUser();
		
		
        Usuario user = userRepository.findByUsername(principal.getName());
        System.out.println(user.getProfileUrl());
        //request.getSession().setAttribute("userId", u.getId());
		//System.out.println(userDetail.getUsername());
		//model.addAttribute("videoSource", request.getParameter("videoSrc"));
		model.addAttribute("videoSource", videoSrc);
		model.addAttribute("videoType", type);
		return new ModelAndView("PlayerEventos");
	}
}
