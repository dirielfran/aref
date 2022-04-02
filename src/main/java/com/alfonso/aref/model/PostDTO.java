package com.alfonso.aref.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

	Long id;
	Long userId;
	String title;
	String body;
}
