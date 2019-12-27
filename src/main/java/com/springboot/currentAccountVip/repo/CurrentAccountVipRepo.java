package com.springboot.currentAccountVip.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.currentAccountVip.document.CurrentAccountVip;

import reactor.core.publisher.Mono;

public interface CurrentAccountVipRepo extends ReactiveMongoRepository<CurrentAccountVip, String> {

	public Mono<CurrentAccountVip> findByNumberAccount(String numberAccount);
}
