package com.springboot.currentAccountVip.service;

import com.springboot.currentAccountVip.document.CurrentAccountVip;
import com.springboot.currentAccountVip.dto.AccountDto;
import com.springboot.currentAccountVip.dto.CurrentAccountVipDto;
import com.springboot.currentAccountVip.dto.OperationDto;
import com.springboot.currentAccountVip.dto.PersonalVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountVipInterface{

	public Flux<CurrentAccountVip> findAll();
	public Mono<CurrentAccountVip> findById(String id);
	public Mono<CurrentAccountVip> save(CurrentAccountVip CurrentAccountVip);
	public Mono<CurrentAccountVip> update(CurrentAccountVip CurrentAccountVip ,String id);
	public Mono<Void> delete(CurrentAccountVip CurrentAccountVip);
	
	public Mono<CurrentAccountVip> findByNumAccount(String numAccount);
	public Mono<CurrentAccountVip> saveOperation(OperationDto operationDto);	
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto);     
	public Mono<CurrentAccountVipDto> saveHeadlines (CurrentAccountVipDto CurrentAccountVipDto);
	

}
