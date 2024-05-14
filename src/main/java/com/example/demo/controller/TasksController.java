package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
	
	@GetMapping({"/",  "/login"})
	public String top() {
		return "login";
	}
	
	@GetMapping("/todo")
	public String tasksView() {
		return "tasks";
	}
}
