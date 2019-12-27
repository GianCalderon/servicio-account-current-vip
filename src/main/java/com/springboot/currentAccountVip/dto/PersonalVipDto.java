package com.springboot.currentAccountVip.dto;

import com.springboot.currentAccountVip.util.CodAccount;

import lombok.Data;

@Data
public class PersonalVipDto {

	private String idAccount;
	private String numberAccount="xxxxxxxxxxxxxx";
	private String nameAccount=CodAccount.NAME_CURRENT_ACCOUNT;
	

	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;



}
