package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Tasks;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TasksController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	java.util.Date utilDate;
	
	

	//ホーム表示
	@GetMapping("/todo")
	public String tasksView(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		List<Tasks> taskListAll = taskRepository.findAll();
		model.addAttribute("tasks", taskListAll);
		
		return "tasks";
	}


	//タスク追加
	@GetMapping("/todo/add")
	public String taskAddGet() {
		return "addTask";
	}

	@PostMapping("/todo/add")
	public String taskAddPost(
			@RequestParam(name = "priority", defaultValue = "") Integer priority,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "closingDate", defaultValue = "") Date closingDate,
			@RequestParam(name = "progress", defaultValue = "") Integer progress,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {
		Tasks task = new Tasks(priority, title, closingDate, progress, memo);
		taskRepository.save(task);
		
		return "redirect:/todo";
	}

	//更新
	@GetMapping("/todo/{taskId}/edit")
	public String editGet(
			@PathVariable("taskId") Integer taskId,
			Model model) {
		Tasks task = taskRepository.findById(taskId).get();
		model.addAttribute("task", task);
		return "editTask";
	}

	@PostMapping("/todo/{taskId}/edit")
	public String editPost(
			@PathVariable("taskId") Integer taskId,
			@RequestParam(name = "priority", defaultValue = "") Integer priority,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "closingDate") Date closingDate,
			@RequestParam(name = "progress", defaultValue = "") Integer progress,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {
		if (closingDate == null) {
			model.addAttribute("nullDateMessage", "日付の入力は必須です");
			return "editTask";
		}
		Tasks task = new Tasks(taskId, priority, title, closingDate, progress, memo);
		System.out.println("進捗度は" + closingDate);
		taskRepository.save(task);
		return "redirect:/todo";
	}

	//削除
	@PostMapping("/todo/{taskId}/delete")
	public String delete(
			@PathVariable("taskId") Integer taskId,
			Model model) {
		taskRepository.deleteById(taskId);
		return "redirect:/todo";
	}

	//完了
	@PostMapping("/todo/{taskId}/complete")
	public String complete(
			@PathVariable("taskId") Integer taskId,
			Model model) {
		Tasks task = taskRepository.findById(taskId).get();
		task.setProgress(3);
		taskRepository.save(task);
		return "redirect:/todo";
	}

	//指定検索
	@GetMapping("/todo/sort")
	public String sort(
			@RequestParam(name = "category", defaultValue = "") String priority,
			Model model) {
		
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		
		switch (priority) {
		case "高":
			List<Tasks> taskListHigh = taskRepository.findByCategoryIdIs(1);
			model.addAttribute("tasks", taskListHigh);
			return "tasks";

		case "中":
			List<Tasks> taskListMiddle = taskRepository.findByCategoryIdIs(2);
			model.addAttribute("tasks", taskListMiddle);
			return "tasks";

		case "低":
			List<Tasks> taskListLow = taskRepository.findByCategoryIdIs(3);
			model.addAttribute("tasks", taskListLow);
			return "tasks";

		default:
			List<Tasks> taskListAll = taskRepository.findAll();
			model.addAttribute("tasks", taskListAll);
			return "tasks";
		}
	}
}


