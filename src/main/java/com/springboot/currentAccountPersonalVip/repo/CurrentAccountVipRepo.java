package com.springboot.currentAccountPersonalVip.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.currentAccountPersonalVip.document.CurrentAccountVip;

import reactor.core.publisher.Mono;

public interface CurrentAccountVipRepo extends ReactiveMongoRepository<CurrentAccountVip, String> {

	public Mono<CurrentAccountVip> findByNumberAccount(String numberAccount);
}
