package com.springboot.currentAccountPersonalVip.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccountPersonalVip.client.PersonalClientVip;
import com.springboot.currentAccountPersonalVip.document.CurrentAccountVip;
import com.springboot.currentAccountPersonalVip.dto.CurrentAccountVipDto;
import com.springboot.currentAccountPersonalVip.repo.CurrentAccountVipRepo;
import com.springboot.currentAccountPersonalVip.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountVipImpl implements CurrentAccountVipInterface {


	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountVipImpl.class);

	
	@Autowired
    CurrentAccountVipRepo repo;
	
	@Autowired
	UtilConvert convert;

	@Autowired
	PersonalClientVip webCLient;
//	
//	@Autowired
//	ManageOperationClient webCLientOpe;
	
	
	@Override
	public Flux<CurrentAccountVip> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccountVip> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<CurrentAccountVip> save(CurrentAccountVip currentAccountVip) {
		// TODO Auto-generated method stub
		return repo.save(currentAccountVip);
	}
	
	@Override
	public Mono<CurrentAccountVip> update(CurrentAccountVip currentAccountVip, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setName(currentAccountVip.getName());
		s.setNumberAccount(currentAccountVip.getNumberAccount());
		s.setBalance(currentAccountVip.getBalance());
		s.setState(currentAccountVip.getState());
		s.setTea(currentAccountVip.getTea());
		s.setUpdateDate(new Date());
		s.setIdOperation(currentAccountVip.getIdOperation());
		
		return repo.save(s);
		});
	}
	

	@Override
	public Mono<Void> delete(CurrentAccountVip currentAccountVip) {
		
		return repo.delete(currentAccountVip);
	}
	
	
	@Override
	public Mono<CurrentAccountVipDto> saveDto(CurrentAccountVipDto currentAccountVipDto) {
	
		LOGGER.info("service --> "+currentAccountVipDto.toString());

		return save(convert.convertSavingsAccountPersonalVip(currentAccountVipDto)).flatMap(sa -> {

			currentAccountVipDto.getHolders().forEach(p -> {

				p.setNameAccount(sa.getName());
				p.setIdCuenta(sa.getId());

				webCLient.save(p).block();

			});

			return Mono.just(currentAccountVipDto);
		});
		
	}


	
	
//	@Override
//	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount) {
//		
//		return repo.findByNumberAccount(numAccount).flatMap(s -> {
//
//		s.setNumberAccount(savingsAccount.getNumberAccount());
//		s.setBalance(savingsAccount.getBalance());
//		s.setState(savingsAccount.getState());
//		return repo.save(s);
//		});
//	}
//	
//	@Override
//	public Mono<SavingsAccountPersonalVip> findByNumAccount(String numAccount) {
//		
//		return repo.findByNumberAccount(numAccount);
//	}
//	
//	
//	@Override
//	public Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto) {
//	
//	
//
//		return save(convert.convertSavingsAccount(savingsAccountDto)).flatMap(sa -> {
//
//			savingsAccountDto.getHolders().forEach(p -> {
//
//				p.setNameAccount(sa.getName());
//				p.setIdCuenta(sa.getId());
//
//				webCLient.save(p).block();
//
//			});
//
//			return Mono.just(savingsAccountDto);
//		});
//		
//	}
//	
//	@Override
//	public Mono<SavingsAccount> saveOperation(OperationDto operationDto) {
//		
//		
//		
//	return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{
//		
//	
//		if(operationDto.getTipoMovement().equals("debito")) {
//			
//			p.setBalance(p.getBalance()-operationDto.getAmount());
//			return repo.save(p);
//			
//		}else if(operationDto.getTipoMovement().equals("abono")) {
//			
//			p.setBalance(p.getBalance()+operationDto.getAmount());
//			return repo.save(p);
//		}
//		
//		return repo.save(p);
//	
//
//
//	});
//				
//				
//
//
//  }

}