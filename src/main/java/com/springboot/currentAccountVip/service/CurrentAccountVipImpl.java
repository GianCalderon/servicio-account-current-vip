package com.springboot.currentAccountVip.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.currentAccountVip.client.PersonalVipClient;
import com.springboot.currentAccountVip.document.CurrentAccountVip;
import com.springboot.currentAccountVip.dto.AccountClient;
import com.springboot.currentAccountVip.dto.AccountDto;
import com.springboot.currentAccountVip.dto.CurrentAccountVipDto;
import com.springboot.currentAccountVip.dto.OperationDto;
import com.springboot.currentAccountVip.dto.PersonalVipDto;
import com.springboot.currentAccountVip.repo.CurrentAccountVipRepo;
import com.springboot.currentAccountVip.util.CodAccount;
import com.springboot.currentAccountVip.util.UtilConvert;

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
	PersonalVipClient client;


	
	@Override
	public Flux<CurrentAccountVip> findAll() {
	
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccountVip> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<CurrentAccountVip> save(CurrentAccountVip CurrentAccountVip) {
		
		CurrentAccountVip.setCreateDate(new Date());
		CurrentAccountVip.setUpdateDate(new Date());
		CurrentAccountVip.setNameAccount("Cuenta-Corriente");
		CurrentAccountVip.setIdOperation(new ArrayList<String>());
		
		return repo.save(CurrentAccountVip);
	}

	@Override
	public Mono<CurrentAccountVip> update(CurrentAccountVip CurrentAccountVip, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(CurrentAccountVip.getNameAccount());
			s.setNumberAccount(CurrentAccountVip.getNumberAccount());
			s.setBalance(CurrentAccountVip.getBalance());
			s.setState(CurrentAccountVip.getState());
			s.setTea(CurrentAccountVip.getTea());
			s.setUpdateDate(CurrentAccountVip.getUpdateDate());
			s.setCreateDate(CurrentAccountVip.getCreateDate());
			s.setIdOperation(CurrentAccountVip.getIdOperation());
			
			
			return repo.save(s);
			});
	}
	

	@Override
	public Mono<Void> delete(CurrentAccountVip CurrentAccountVip) {
		
		return repo.delete(CurrentAccountVip);
	}

	
	@Override
	public Mono<CurrentAccountVip> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}
	
	
	@Override
	public Mono<CurrentAccountVip> saveOperation(OperationDto operationDto) {
		
		return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{

			if(operationDto.getTipoMovement().equals("debito")) {
				
				p.setBalance(p.getBalance()-operationDto.getAmount());
				return repo.save(p);
				
			}else if(operationDto.getTipoMovement().equals("abono")) {
				
				p.setBalance(p.getBalance()+operationDto.getAmount());
				return repo.save(p);
			}
			
			return repo.save(p);

		});
	}
	

	@Override
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto) {
	
     return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
			
			int cont = 0;

		     for (int i = 0; i < cuentas.size(); i++) {

					AccountClient obj = cuentas.get(i);

					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;

				}
		     
				if (cont == 0) {

					return repo.save(convert.convertAccountDto(accountDto)).flatMap(cuenta -> {

						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

							LOGGER.info("Flujo Inicial ---->: " + titular.toString());

							titular.setIdAccount(cuenta.getId());
							titular.setNameAccount(cuenta.getNameAccount());
							titular.setNumberAccount(cuenta.getNumberAccount());

							LOGGER.info("Flujo Final ----->: " + titular.toString());

							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
								
								p.setIdAccount(cuenta.getId());
								return Mono.just(p);
							});

						});

					});

				} else {

					return Mono.empty();
				}

			});
	}

	@Override
	public Mono<CurrentAccountVipDto> saveHeadlines(CurrentAccountVipDto CurrentAccountVipDto) {
		
		return save(convert.convertCurrentAccountVipPer(CurrentAccountVipDto)).flatMap(cuenta -> {

			CurrentAccountVipDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular);

			});

			return Mono.just(CurrentAccountVipDto);
		});
	}
	

}