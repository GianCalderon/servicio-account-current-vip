package com.springboot.currentAccountVip.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.currentAccountVip.document.CurrentAccountVip;
import com.springboot.currentAccountVip.dto.AccountDto;
import com.springboot.currentAccountVip.dto.CurrentAccountVipDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public CurrentAccountVip convertCurrentAccountVipPer(CurrentAccountVipDto CurrentAccountVipDto) {

		CurrentAccountVip  CurrentAccountVip = new CurrentAccountVip();

		CurrentAccountVip.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		CurrentAccountVip.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		CurrentAccountVip.setState(CurrentAccountVipDto.getState());
		CurrentAccountVip.setBalance(CurrentAccountVipDto.getBalance());
		CurrentAccountVip.setTea(CurrentAccountVipDto.getTea());
		CurrentAccountVip.setCreateDate(new Date());
		CurrentAccountVip.setUpdateDate(new Date());
		CurrentAccountVip.setIdOperation(new ArrayList<String>());
		
		
		return CurrentAccountVip;

	}
	

	
	

	
	public CurrentAccountVip convertAccountDto(AccountDto accountDto) {

		CurrentAccountVip  CurrentAccountVip = new CurrentAccountVip();

		CurrentAccountVip.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		CurrentAccountVip.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		CurrentAccountVip.setState(accountDto.getState());
		CurrentAccountVip.setBalance(accountDto.getBalance());
		CurrentAccountVip.setTea(accountDto.getTea());
		CurrentAccountVip.setCreateDate(new Date());
		CurrentAccountVip.setUpdateDate(new Date());
		CurrentAccountVip.setIdOperation(new ArrayList<String>());

		return CurrentAccountVip;

	}


}
