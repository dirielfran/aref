package com.alfonso.aref.service;

import com.alfonso.aref.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentService implements ICommentService{


	@Autowired
	RestTemplate restTemplate;

	/**
	 *
	 * @return
	 */
	@Override
	public List<CommentDTO> findAll() {
		String uri= "https://jsonplaceholder.typicode.com/comments";
		CommentDTO[] result =restTemplate.getForObject(uri, CommentDTO[].class);
		System.out.println("llegyue");
		return Arrays.asList(result);
	}
}
