package org.baeldung.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoPlayerController {

	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}
}
