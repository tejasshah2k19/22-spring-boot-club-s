package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.UserBean;
import com.dao.UserDao;

@Controller
public class SessionController {

	@Autowired
	UserDao userDao;

	@GetMapping("/")
	public String welcome() {
		return "Welcome";
	}

	@GetMapping("/signup")
	public String signup() {
		return "Signup";// view jsp
	}

	@PostMapping("/saveuser")
	public String saveUser(UserBean user) {
		System.out.println(user.getFirstName());
		userDao.insertUser(user);
		return "Login";
	}

	@GetMapping("/login")
	public String login() {

		return "Login";
	}

	@PostMapping("/authenticate")
	public String authenticate(UserBean user) {

		UserBean dbUser = userDao.getUserByEmail(user.getEmail());
		if (dbUser == null) {
			return "Login";
		} else if (dbUser.getPassword().equals(user.getPassword())) {
			return "Home";
		} else {
			return "Login";
		}
	}

	@GetMapping("/forgetpassword")
	public String forgetPassword() {
		return "ForgetPassword";
	}

	@PostMapping("/forgetpassword")
	public String forgetpassword(UserBean user, Model model) {
		// email ?
		// otp => send mail
		UserBean dbUser = userDao.getUserByEmail(user.getEmail());
		if (dbUser == null) {
			model.addAttribute("msg", "Invalid Email");
			return "ForgetPassword";
		} else {
			// send otp
			int otp = (int) (Math.random() * 1000000);
			userDao.updateOtp(user.getEmail(), otp);

			model.addAttribute("msg", "Otp Sent on your email");

			return "ResetPassword";
		}
	}

	@PostMapping("/updatepassword")
	public String updatePassword(UserBean user, Model model) {
		System.out.println(user.getEmail());
		System.out.println(user.getOtp());
		System.out.println(user.getPassword());
		UserBean dbUser = userDao.getUserByEmail(user.getEmail());
		if (dbUser == null) {
			model.addAttribute("msg", "Invalid EMAIL");
			return "ResetPassword";
		} else {
			System.out.println("original otp = > "+dbUser.getOtp());
			if (dbUser.getOtp().equals(user.getOtp())) {
				userDao.updatePassword(user.getEmail(), user.getPassword());
				model.addAttribute("msg", "Password successfully modified...");
				return "Login";
			} else {
				model.addAttribute("msg", "Invalid OTP");
				return "ResetPassword";

			}
		}

	}

}
