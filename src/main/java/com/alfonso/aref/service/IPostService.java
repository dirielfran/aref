package com.alfonso.aref.service;

import com.alfonso.aref.model.PostDTO;

import java.util.List;

public interface IPostService {
	int postsForUser(Long idUser);
	List<PostDTO> postsOfUser(Long idUser);
	List<PostDTO> findByBody(String keyWord);
	List<PostDTO> findAll();

	List<PostDTO> mostCommentedPost();
}
