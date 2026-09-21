package vn.iotstar.example2.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.example2.security.Example2Principal;
@ControllerAdvice(annotations = Controller.class) public class CurrentUserAdvice { @ModelAttribute("currentUser") Example2Principal currentUser(Authentication authentication) { return authentication != null && authentication.getPrincipal() instanceof Example2Principal principal ? principal : null; } }
