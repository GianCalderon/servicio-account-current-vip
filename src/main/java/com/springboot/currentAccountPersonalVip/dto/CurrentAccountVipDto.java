package com.springboot.currentAccountPersonalVip.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentAccountVipDto {

	private String numberAccount;
	
	private Double tea;

	private String state;

	private Double balance;
 
	private List<PersonalVipDto> holders;	
	
	

}
