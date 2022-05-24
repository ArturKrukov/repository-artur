package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.tables.*;

@Controller
public class WebPageController{

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		return "registration";
	}
	
	@GetMapping("/posts")
	public String posts(Model model) {
		Iterable <Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);
		return "posts";
	}
	
	@GetMapping("/posts/addpost")
	public String addPost(Model model) {
		return "addpost";
	}
	
	@PostMapping("/registration")
	public String addPerson(@RequestParam String email, @RequestParam String password) {
		Person person = new Person(email, password);
		personRepository.save(person);
		return "redirect:/";
	}
	
	@PostMapping("/posts/addpost")
	public String addPost(@RequestParam String title, @RequestParam String fulltext) {
		Post post = new Post(title, fulltext);
		postRepository.save(post);
		return "redirect:/posts";
	}
}
