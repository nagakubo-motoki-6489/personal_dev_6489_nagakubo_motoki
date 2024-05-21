package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

	//ゲーム一覧
	@GetMapping("/todo/game")
	public String gameMenu() {
		return "Game/Game_menu";
	}
	
	//ライツアウト
	@GetMapping("/todo/game/lightsOut")
	public String lightsOut() {
		return "Game/Game_lightsOut";
	}
	//ブロック崩し
	@GetMapping("/todo/game/ticTacToe")
	public String ticTacToe() {
		return "Game/Game_tic-tac-toe";
	}
}
