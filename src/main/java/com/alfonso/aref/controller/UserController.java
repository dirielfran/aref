package com.alfonso.aref.controller;

import com.alfonso.aref.model.UserDTO;
import com.alfonso.aref.service.IPostService;
import com.alfonso.aref.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

	@Autowired
	IUserService iUserService;

	@Autowired
	IPostService iPostService;

	@GetMapping("/users")
	public List<UserDTO> getUsers(){
		return iUserService.findAll();
	}

	@GetMapping("/user/{id}")
	public UserDTO getUserById(@PathVariable("id") Long id){
		return iUserService.findById(id);
	}

	@GetMapping("/user")
	public UserDTO getUserByUsername(@RequestParam String username){
		return iUserService.findByUsername(username);
	}

	@GetMapping("/userEmail")
	public UserDTO getUserByEmail(@RequestParam String email){
		return iUserService.findByEmail(email);
	}

	@GetMapping("/userCity")
	public List<UserDTO> getUsersByCity(@RequestParam String city){
		return iUserService.findByCity(city);
	}

	//Metodo para consultar cuantos artículos tiene publicados un determinado usuario (Por ID del usuario)
	@GetMapping("/postsForUser/{id}")
	public ResponseEntity<?> getPostForUsers(@PathVariable("id") Long idUser){
		//Se crea Map para el envio de mensaje de error en el ResponseEntity
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("message", " Este usuario tiene "+ iPostService.postsForUser(idUser)+" Posts");
		} catch (Exception e) {
			response.put("message", "Error al realizar la consulta.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
