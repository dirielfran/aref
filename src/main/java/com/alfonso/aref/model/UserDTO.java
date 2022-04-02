package com.alfonso.aref.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	Long id;
	String name;
	String username;
	String email;
	AddressDTO address;
	String phone;
	String website;
	CompanyDTO company;


	
}
