package com.alfonso.aref.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	String street;
	String suite;
	String city;
	String zipCode;
	GeoDTO geo;
}
