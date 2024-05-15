package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;
	
	@Autowired
	UserRepository userRepository;
	
	//ログイン
	@GetMapping({ "/", "/login" })
	public String top() {
		session.invalidate();
		return "login";
		
	}
	
	
	@PostMapping("/login")
	public String loginTasksView(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model
			) {
		//テキストボックスがnullかチェック
		int nullErrorCheck = 0;
		if(email.length() == 0) {
			model.addAttribute("nullEmailMessage", "メールアドレスが入力されていません");
			nullErrorCheck += 1;
		}
		if(password.length() == 0) {
			model.addAttribute("nullPassMessage", "パスワードが入力されていません");
			nullErrorCheck += 1;
		}
		if(nullErrorCheck != 0) {
			return "login";
		}
		
		//登録処理
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if(userList == null || userList.size() == 0) {
			model.addAttribute("noInfoMessage", "入力された情報に一致するユーザ情報がありませんでした");
			return "login";
		}
		
		User user = userList.get(0);
		account.setId(user.getId());
		account.setName(user.getName());
		
		return "redirect:/todo";
	}

	//ユーザー登録
	@GetMapping("/todo/userAdd")
	public String usertAddGet() {
		return "addUser";
	}
	
	@PostMapping("/todo/userAdd")
	public String userAddPost(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "pass", defaultValue = "") String pass,
			@RequestParam(name = "passCheck", defaultValue = "") String passCheck,
			Model model
			) {
		//nullチェック
		int nullErrorCheck = 0;
		if(name.length() == 0) {
			model.addAttribute("nullNameMessage", "名前が入力されていません");
			nullErrorCheck += 1;
		}
		if(email.length() == 0) {
			model.addAttribute("nullEmailMessage", "メールアドレスが入力されていません");
			nullErrorCheck += 1;
		}
		if(pass.length() == 0 || passCheck.length() == 0) {
			model.addAttribute("nullPassMessage", "パスワードが入力されていません");
			nullErrorCheck += 1;
		}
		if(nullErrorCheck != 0) {
			return "addUser";
		}
		
		//パスワード一致チェック
		if(!pass.equals(passCheck)) {
			model.addAttribute("passError", "入力されたパスワードが違います");
			return "addUser";
		}
		//テーブル追加
		User user = new User(name, email,pass);
		userRepository.save(user);
		
		return "login";
	}
}
