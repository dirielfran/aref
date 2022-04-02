package com.alfonso.aref.service;

import com.alfonso.aref.model.UserDTO;

import java.util.List;

public interface IUserService {
	
	UserDTO findById(Long idUser);
	List<UserDTO> findAll();
	UserDTO findByUsername(String username);
	UserDTO findByEmail(String email);
	List<UserDTO> findByCity(String city);
}
