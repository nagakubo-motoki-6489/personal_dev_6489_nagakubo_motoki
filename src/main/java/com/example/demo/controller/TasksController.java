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
	public String tasksView(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		List<Tasks> completeTasks = taskRepository.findByProgressIs(3);
		System.out.println(completeTasks.size());
		model.addAttribute("completeTasks", completeTasks);

		List<Tasks> taskListAll = taskRepository.findAllByOrderByClosingDateAsc();
		taskListAll.removeAll(taskRepository.findByProgressIs(3));
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
		if (priority == null || progress == null || memo == null) {
			model.addAttribute("nullMessage", "全ての情報を入力してください");
			return "addTask";
		}
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
		if (priority == null || closingDate == null || title == null || progress == null) {
			Tasks task = taskRepository.findById(taskId).get();
			model.addAttribute("task", task);
			model.addAttribute("nullMessage", "全ての情報を入力してください");
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

	//進行中
	@PostMapping("/todo/{taskId}/onTime")
	public String onTime(
			@PathVariable("taskId") Integer taskId,
			Model model) {
		Tasks task = taskRepository.findById(taskId).get();
		task.setProgress(2);
		taskRepository.save(task);
		return "redirect:/todo";
	}

	//未完了
	@PostMapping("/todo/{taskId}/notComplete")
	public String noComplete(
			@PathVariable("taskId") Integer taskId,
			Model model) {
		Tasks task = taskRepository.findById(taskId).get();
		task.setProgress(1);
		taskRepository.save(task);
		return "redirect:/todo";
	}

	//指定検索(優先度)
	@GetMapping("/todo/sort")
	public String sort(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "category", defaultValue = "") Integer priority,
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("categoryId", priority);

		if (priority != null && sort.equals("Asc")) {
			//優先度あり昇順
			List<Tasks> taskListHigh = taskRepository.findByCategoryIdOrderByClosingDateAsc(priority);
			taskListHigh.removeAll(taskRepository.findByProgressIs(3));
			model.addAttribute("tasks", taskListHigh);
			return "tasks";

		} else if (priority != null && sort.equals("Desc")) {
			//優先度あり降順
			List<Tasks> taskListHigh = taskRepository.findByCategoryIdOrderByClosingDateDesc(priority);
			taskListHigh.removeAll(taskRepository.findByProgressIs(3));
			model.addAttribute("tasks", taskListHigh);
			return "tasks";
		} else if(priority != null) {
			//優先度あり指定なし
			switch (priority) {
			case 1://高
				List<Tasks> taskListHigh = taskRepository.findByCategoryIdIs(1);
				taskListHigh.removeAll(taskRepository.findByProgressIs(3));
				model.addAttribute("tasks", taskListHigh);
				return "tasks";

			case 2://中
				List<Tasks> taskListMiddle = taskRepository.findByCategoryIdIs(2);
				taskListMiddle.removeAll(taskRepository.findByProgressIs(3));
				model.addAttribute("tasks", taskListMiddle);
				return "tasks";

			case 3://低
				List<Tasks> taskListLow = taskRepository.findByCategoryIdIs(3);
				taskListLow.removeAll(taskRepository.findByProgressIs(3));
				model.addAttribute("tasks", taskListLow);
				return "tasks";

			default:
				//起こりえないので適当
				return "login";
			}
		}
		//優先度なし
			else {
				if (sort.equals("Asc")) {
					//優先度なし昇順
					List<Tasks> taskListAll = taskRepository.findAllByOrderByClosingDateAsc();
					taskListAll.removeAll(taskRepository.findByProgressIs(3));
					model.addAttribute("tasks", taskListAll);
					
					List<Tasks> completeTasks = taskRepository.findByProgressIs(3);
					model.addAttribute("completeTasks", completeTasks);
					return "tasks";
				} else {
					//優先度なし降順
					List<Tasks> taskListAll = taskRepository.findAllByOrderByClosingDateDesc();
					taskListAll.removeAll(taskRepository.findByProgressIs(3));
					model.addAttribute("tasks", taskListAll);
					
					List<Tasks> completeTasks = taskRepository.findByProgressIs(3);
					model.addAttribute("completeTasks", completeTasks);
					return "tasks";
				}
			}
		}

	//指定検索(進捗度)
	@GetMapping("/todo/progress")
	public String sort(
			@RequestParam(name = "progress", defaultValue = "") Integer progress,
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		switch (progress) {
		case 1:
			List<Tasks> taskListUntil = taskRepository.findByProgressIs(1);
			model.addAttribute("tasks", taskListUntil);
			return "tasks";

		case 2:
			List<Tasks> taskListNow = taskRepository.findByProgressIs(2);
			model.addAttribute("tasks", taskListNow);
			return "tasks";

		default:
			List<Tasks> taskListAll = taskRepository.findAll();
			model.addAttribute("tasks", taskListAll);
			return "tasks";
		}
	}

	//カレンダー
	@GetMapping("/todo/calendar")
	public String calendar(Model model) {
		List<Tasks> taskListAll = taskRepository.findAll();
		//taskListAll.removeAll(taskRepository.findByProgressIs(3));
		model.addAttribute("tasks", taskListAll);
		
		return "calendar";
	}
}
