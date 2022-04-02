package com.alfonso.aref.service;

import com.alfonso.aref.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

	@Autowired
	RestTemplate restTemplate;

	/**
	 *
	 * @return
	 */
	@Override
	public List<UserDTO> findAll() {
		String uri= "https://jsonplaceholder.typicode.com/users";
		UserDTO[] result =restTemplate.getForObject(uri, UserDTO[].class);
		System.out.println("llegyue");
		return Arrays.asList(result);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public UserDTO findById(Long id) {
		String uri= "https://jsonplaceholder.typicode.com/users/"+id;
		UserDTO result =restTemplate.getForObject(uri, UserDTO.class);
		System.out.println("llegyue");
		return result;
	}

	/**
	 *
	 * @param username
	 * @return
	 */
	@Override
	public UserDTO findByUsername(String username) {
		List<UserDTO> list = findAll();
		UserDTO user = list.stream().filter(predicate -> predicate.getUsername().equals(username)).findFirst().orElse(null);
		return user;
	}

	/**
	 *
	 * @param email
	 * @return
	 */
	@Override
	public UserDTO findByEmail(String email) {
		List<UserDTO> list = findAll();
		UserDTO user = list.stream().filter(predicate -> predicate.getEmail().equals(email)).findFirst().orElse(null);
		return user;
	}

	/**
	 *
	 * @param city
	 * @return
	 */
	@Override
	public List<UserDTO> findByCity(String city) {
		List<UserDTO> list = findAll();
		return list.stream().filter(predicate -> predicate.getAddress().getCity().equals(city)).collect(Collectors.toList());
	}
}
