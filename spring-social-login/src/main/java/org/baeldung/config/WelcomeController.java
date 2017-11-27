package org.baeldung.config;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

	 @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String baseUrlRedirect(HttpServletRequest request,
	            HttpServletResponse httpServletResponse, Principal principal) {
					 Set<String> roles = AuthorityUtils
				               .authorityListToSet(SecurityContextHolder.getContext()
				                        .getAuthentication().getAuthorities());
					 	System.out.println("PASE POR CONTROLLER !!!!!!!!!!!!!!!");
				        if (roles.contains("ROLE_USER")) {
				            return "redirect:" + request.getRequestURL().append("adminhome.xhtml").toString();
				        }
				        return "redirect:" + request.getRequestURL().append("welcome.xhtml").toString();
	    }
}
