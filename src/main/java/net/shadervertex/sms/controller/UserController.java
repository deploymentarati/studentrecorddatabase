package net.shadervertex.sms.controller;
import java.util.Objects;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import net.shadervertex.sms.dto.UserDto;
import net.shadervertex.sms.entity.User;
import net.shadervertex.sms.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new UserDto());
        return mav;
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "login"; // Return the login form view if there are validation errors
        }
        
        User oauthUser = userService.login(user.getUsername(), user.getPassword());
        
        if (Objects.nonNull(oauthUser)) {    
            return "redirect:/students";
        } else {
            return "redirect:/login";
        }
    }
}



