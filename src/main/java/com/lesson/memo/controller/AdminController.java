package com.lesson.memo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 登録画面
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin/signup";
	}

	// 登録処理
	@PostMapping("/signup")
	public String register(@ModelAttribute @Valid Admin admin, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/signup";
		}

		// pwのハッシュ化
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		adminRepository.save(admin);

		return "redirect:/admin/signin";
	}

	// ログイン画面
	@GetMapping("/signin")
	public String signin() {
		return "admin/signin";
	}
}