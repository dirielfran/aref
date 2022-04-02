package com.alfonso.aref.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

	Long id;
	Long postId;
	String name;
	String email;
	String body;
}
