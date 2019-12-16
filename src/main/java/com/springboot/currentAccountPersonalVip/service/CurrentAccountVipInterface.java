package com.springboot.currentAccountPersonalVip.service;

import com.springboot.currentAccountPersonalVip.document.CurrentAccountVip;
import com.springboot.currentAccountPersonalVip.dto.CurrentAccountVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountVipInterface{

	public Flux<CurrentAccountVip> findAll();
	public Mono<CurrentAccountVip> findById(String id);
	public Mono<CurrentAccountVip> save(CurrentAccountVip currentAccountVip);
	public Mono<CurrentAccountVip> update(CurrentAccountVip currentAccountVip, String id);
	public Mono<Void> delete(CurrentAccountVip currentAccountVip);
	
	public Mono<CurrentAccountVipDto> saveDto(CurrentAccountVipDto currentAccountVipDto);
	
	
//	public Mono<SavingsAccountPersonalVipDto> saveDto(SavingsAccountPersonalVipDto savingsAccountPersonalVipDto);
//	public  Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto);
//	
//	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount);
//	
//	public Mono<SavingsAccount> findByNumAccount(String id);
//	
//	public Mono<SavingsAccount> saveOperation(OperationDto operationDto);
	
}
