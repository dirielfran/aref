package com.alfonso.aref.service;

import com.alfonso.aref.model.CommentDTO;

import java.util.List;

public interface ICommentService {
	List<CommentDTO> findAll();
}
