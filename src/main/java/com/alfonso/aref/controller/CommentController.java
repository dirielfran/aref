package com.alfonso.aref.controller;

import com.alfonso.aref.model.CommentDTO;
import com.alfonso.aref.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

	@Autowired
	ICommentService iCommentService;

	@GetMapping("/findAll")
	public List<CommentDTO> findAll(){
		return iCommentService.findAll();
	}



}
