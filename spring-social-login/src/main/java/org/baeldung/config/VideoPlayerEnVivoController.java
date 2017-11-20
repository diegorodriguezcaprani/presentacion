package org.baeldung.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoPlayerEnVivoController {

	@RequestMapping("/enVivo")
	public ModelAndView welcome() {
		return new ModelAndView("VideoPlayerEnVivo");
	}
}