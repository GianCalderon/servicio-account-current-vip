package com.springboot.currentAccountPersonalVip.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.currentAccountPersonalVip.document.CurrentAccountVip;
import com.springboot.currentAccountPersonalVip.dto.CurrentAccountVipDto;
import com.springboot.currentAccountPersonalVip.service.CurrentAccountVipImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/currentAccountVip")
public class CurrentAccountVipController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountVipController.class);

	@Autowired
	CurrentAccountVipImpl service;

	@GetMapping
	public Mono<ResponseEntity<Flux<CurrentAccountVip>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccountVip>> search(@PathVariable String id) {
		
		LOGGER.info("NUMERO DE CUENTA SavinAcount :--->"+id);

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<CurrentAccountVip>> save(@RequestBody CurrentAccountVip currentAccountVip) {

		return service.save(currentAccountVip)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccountVip>> update(@RequestBody CurrentAccountVip currentAccountVip,
			@PathVariable String id) {
		
		
		LOGGER.info("CUENTA PARA ACTUALIZAR :--->"+currentAccountVip.toString());

		return service.update(currentAccountVip, id)
				.map(s -> ResponseEntity.created(URI.create("/api/currentAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	
//	OPERACION QUE EXPONEN SERVICIOS

	@PostMapping("/saveDto")
	public Mono<ResponseEntity<CurrentAccountVipDto>> saveDto(@RequestBody CurrentAccountVipDto currentAccountVipDto) {

		LOGGER.info("controller --> "+currentAccountVipDto.toString());

		return service.saveDto(currentAccountVipDto).map(s -> ResponseEntity.created(URI.create("/api/currentAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
//	
//	@PostMapping("/operation")
//	public Mono<ResponseEntity<SavingsAccount>> operation(@RequestBody OperationDto operationDto) {
//
//		LOGGER.info(operationDto.toString());
//
//		return service.saveOperation(operationDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccountPersonalVip"))
//				.contentType(MediaType.APPLICATION_JSON).body(s));
//
//	}
//	
//	
//	@GetMapping("/cuenta/{numAccount}")
//	public Mono<ResponseEntity<SavingsAccount>> searchByNumDoc(@PathVariable String numAccount) {
//		
//		LOGGER.info("NUMERO DE CUENTA :--->"+numAccount);
//
//		return service.findByNumAccount(numAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
//				.defaultIfEmpty(ResponseEntity.notFound().build());
//
//	}

	
	
	

}
