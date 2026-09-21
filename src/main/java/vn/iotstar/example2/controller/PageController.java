package vn.iotstar.example2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller public class PageController { @GetMapping("/") String home() { return "home"; } @GetMapping("/login") String login() { return "auth/login"; } }
