package com.alfonso.aref.controller;

import com.alfonso.aref.model.PostDTO;
import com.alfonso.aref.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("post")
@RestController
public class PostController {

	@Autowired
	IPostService iPostService;


	@GetMapping("/findAll")
	public List<PostDTO> findAll(){
		return iPostService.findAll();
	}

	@GetMapping("/postsOfUser/{id}")
	public ResponseEntity<?> getPostForUsers(@PathVariable("id") Long idUser){
		//Se crea Map para el envio de mensaje de error en el ResponseEntity
		Map<String, Object> response = new HashMap<>();
		List<PostDTO> postList= new ArrayList<>();
		try {
			postList = iPostService.postsOfUser(idUser);
		} catch (Exception e) {
			response.put("message", "Error al realizar la consulta.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
	}

	@GetMapping("/findByBody")
	public ResponseEntity<?> getPostForUsers(@RequestParam String keyWord){
		//Se crea Map para el envio de mensaje de error en el ResponseEntity
		Map<String, Object> response = new HashMap<>();
		List<PostDTO> postList= new ArrayList<>();
		try {
			postList = iPostService.findByBody(keyWord);
		} catch (Exception e) {
			response.put("message", "Error al realizar la consulta.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
	}

	@GetMapping("mostCommentedPost")
	public List<PostDTO> mostCommentedPost(){
		List<PostDTO> posts = iPostService.mostCommentedPost();
		return posts;
	}
}
