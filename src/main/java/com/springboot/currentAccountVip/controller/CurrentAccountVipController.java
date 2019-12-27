package com.springboot.currentAccountVip.controller;

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

import com.springboot.currentAccountVip.document.CurrentAccountVip;
import com.springboot.currentAccountVip.dto.AccountDto;
import com.springboot.currentAccountVip.dto.CurrentAccountVipDto;
import com.springboot.currentAccountVip.dto.PersonalVipDto;
import com.springboot.currentAccountVip.service.CurrentAccountVipImpl;

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

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<CurrentAccountVip>> save(@RequestBody CurrentAccountVip currentAccountVip) {

		return service.save(currentAccountVip)
				.map(s -> ResponseEntity.created(URI.create("/api/CurrentAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccountVip>> update(@RequestBody CurrentAccountVip currentAccountVip,
			@PathVariable String id) {
		
		LOGGER.info("Controller ---> :"+currentAccountVip.toString());

		return service.update(currentAccountVip, id)
				.map(s -> ResponseEntity.created(URI.create("/api/CurrentAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	
	
	// OPERACIONES QUE EXPONEN SERVICIOS

	
	
	@PostMapping("/saveHeadline")
	public Mono<ResponseEntity<PersonalVipDto>> saveHeadline(@RequestBody AccountDto accountDto) {

		LOGGER.info("Controller ---> :"+accountDto.toString());

		return service.saveHeadline(accountDto).map(s -> ResponseEntity.created(URI.create("/api/CurrentAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<PersonalVipDto>(HttpStatus.CONFLICT));


	}
	
	
	@PostMapping("/saveHeadlines")
	public Mono<ResponseEntity<CurrentAccountVipDto>> saveHeadlines(@RequestBody CurrentAccountVipDto currentAccountVipDto) {

		LOGGER.info("Controller ----> "+currentAccountVipDto.toString());

		return service.saveHeadlines(currentAccountVipDto).map(s -> ResponseEntity.created(URI.create("/api/CurrentAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<CurrentAccountVipDto>(HttpStatus.CONFLICT));

	}
	

	
	
	@GetMapping("/cuenta/{numberAccount}")
	public Mono<ResponseEntity<CurrentAccountVip>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

}
