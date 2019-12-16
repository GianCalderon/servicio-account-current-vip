package com.springboot.currentAccountPersonalVip.util;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.currentAccountPersonalVip.document.CurrentAccountVip;
import com.springboot.currentAccountPersonalVip.dto.CurrentAccountVipDto;

@Component
public class UtilConvert {
	
	
	public CurrentAccountVip convertSavingsAccountPersonalVip(CurrentAccountVipDto currentAccountVipDto) {

		CurrentAccountVip currentAccountVip = new CurrentAccountVip();

		currentAccountVip.setName("Cuenta-Ahorro-PersonalVip");
		currentAccountVip.setNumberAccount(currentAccountVipDto.getNumberAccount());
		currentAccountVip.setTea(currentAccountVipDto.getTea());
		currentAccountVip.setState(currentAccountVipDto.getState());
		currentAccountVip.setBalance(currentAccountVipDto.getBalance());
		currentAccountVip.setCreateDate(new Date());
		currentAccountVip.setUpdateDate(new Date());
		currentAccountVip.setIdOperation(new ArrayList<String>());

		return currentAccountVip;

	}

}
