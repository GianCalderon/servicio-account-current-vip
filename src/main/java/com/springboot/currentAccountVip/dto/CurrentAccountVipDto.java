package com.springboot.currentAccountVip.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentAccountVipDto {

	
	private Double tea;
	private String state;
	private Double balance;
	
	private List<PersonalVipDto> headlines;
	
	
}
