package com.springboot.currentAccountVip.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.currentAccountVip.dto.AccountClient;
import com.springboot.currentAccountVip.dto.PersonalVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalVipClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVipClient.class);
	
	@Autowired
	private WebClient client;
	
	public Flux<PersonalVipDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalVipDto.class));
	}


	public Mono<PersonalVipDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalVipDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	
	public Mono<PersonalVipDto> save(PersonalVipDto personalVipDto) {
		
		LOGGER.info("listo a enviar: "+personalVipDto.toString());
		
		return client.post().uri("/guardar")
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(personalVipDto))
			   .retrieve()
			   .bodyToMono(PersonalVipDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PersonalVipDto> update(PersonalVipDto personalVipDto, String id) {
		
		LOGGER.info("listo enviar Actualizar: "+personalVipDto.toString());
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(personalVipDto)
				   .retrieve()
				   .bodyToMono(PersonalVipDto.class);
	}
	
	
	public Mono<PersonalVipDto> findByNumDoc(String dni) {
		
	
		return client.get()
				.uri("/doc/{dni}",Collections.singletonMap("dni",dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalVipDto.class);
		        
	}
	
	public Flux<AccountClient> extractAccounts(String dni) {

		return client.get()
				.uri("/valid/{dni}",Collections.singletonMap("dni",dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountClient.class);
			
	}
}
