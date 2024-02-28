package pl.online_clinic_management.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.online_clinic_management.api.dto.ClinicUserDTO;
import pl.online_clinic_management.infrastructure.security.OnlineClinicManagementUserDetailsService;

import java.util.Set;

//@Controller
//@AllArgsConstructor
public class LoginController {

    // Login form
//    @RequestMapping("/login.html")
//    public String login() {
//        return "login.html";
//    }
//
//    // Login form with error
//    @RequestMapping("/login-error.html")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login.html";
//    }
/*    private static final String LOGIN = "/login";

    private OnlineClinicManagementUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(
            @ModelAttribute ClinicUserDTO clinicUserDTO,
            RedirectAttributes redirectAttributes
    ) {
        Optional<ClinicUserEntity> userOptional = userDetailsService.authenticateUser(clinicUserDTO.getUserName(), clinicUserDTO.getPassword());

        if (userOptional.isPresent()) {
            // Successful login, store user in session
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userOptional.get().getUserName(), null, Collections.emptyList())
            );
            redirectAttributes.addFlashAttribute("success", "Zalogowano pomyślnie!");
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Błędne dane logowania!");
            return "redirect:/login";
        }
    }*/
}
