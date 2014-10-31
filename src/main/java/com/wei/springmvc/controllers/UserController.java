package com.wei.springmvc.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.springmvc.exceptions.UserException;
import com.wei.springmvc.models.User;


@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, User> users = new HashMap<String, User>();

	public UserController() {
		users.put("santy", new User("santy","123123123","ww","ww467@msstate.edu"));
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("users", users);
		return "/user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute("user") User user){
		//model.addAttribute(new User());
		return "/user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user, BindingResult result){
		if(result.hasErrors()){
			return "/user/add";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String getView(@PathVariable String username, Model model){
		model.addAttribute(users.get(username));
		return "/user/view";
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET,params="json")
	@ResponseBody
	public User getView(@PathVariable String username){
		return users.get(username);
	}
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.GET)
	public String update(@PathVariable String username, Model model){
		model.addAttribute(users.get(username));
		return "/user/update";
	}
	
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.POST)
	public String update(@PathVariable String username, @Validated User user, BindingResult result){
		if(result.hasErrors()){
			return "/user/update";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable String username){
		users.remove(username);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public String login(String username, String password, HttpSession session){
		if(!users.containsKey(username)){
			throw new UserException("Username not found");
		}
		
		User u = users.get(username);
		if(!u.getPassword().equals(password)){
			throw new UserException("Wrong password");
		}
		
		session.setAttribute("loginUser", u);
		return "redirect:/user/users";
	}
	
	@ExceptionHandler(value={UserException.class})
	public String handlerException(UserException ex, HttpServletRequest req){
		req.setAttribute("ex", ex);
		return "error";
	}
}
