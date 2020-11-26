package edu.hanu.studentManagement.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.hanu.studentManagement.model.New;
import edu.hanu.studentManagement.service.NewService;
import edu.hanu.studentManagement.service.SearchService;
import edu.hanu.studentManagement.service.SecurityService;

@Controller
public class HomeController {
	
	@Autowired
	NewService newService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private MessageSource messages;
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/home")
	public String user(@RequestParam(value="search", required=false) String search, Model model) {
		List<New> searchResults = null;
		try {
			searchResults = searchService.search(search);
		} catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("news", getNews());
		model.addAttribute("search", searchResults);
		return "homepage";
	}
	
	@ModelAttribute("news")
	public List<New> getNews() {
		return newService.getNews();
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "forgotPassword";
	}
	
	@GetMapping("/user/changePassword")
    public ModelAndView showChangePasswordPage(final ModelMap model, @RequestParam("token") final String token) {
        final String result = securityService.validatePasswordResetToken(token);

        if(result != null) {
            String messageKey = "auth.message." + result;
            model.addAttribute("messageKey", messageKey);
            return new ModelAndView("redirect:/login", model);
        } else {
            model.addAttribute("token", token);
            return new ModelAndView("redirect:/updatePassword");
        }
    }
	
	@GetMapping("/updatePassword")
    public ModelAndView updatePassword(final HttpServletRequest request, final ModelMap model, @RequestParam("messageKey" ) final Optional<String> messageKey) {
        Locale locale = request.getLocale();
        model.addAttribute("lang", locale.getLanguage());
        messageKey.ifPresent( key -> {
                    String message = messages.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );

        return new ModelAndView("updatePassword", model);
    }

}
