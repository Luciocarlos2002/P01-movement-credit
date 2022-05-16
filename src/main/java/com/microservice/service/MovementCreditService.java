package com.microservice.service;

import com.microservice.model.MovementCredit;
import com.microservice.repository.MovementCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovementCreditService {

    private final MovementCreditRepository movementCreditRepository;

    public Flux<MovementCredit> getAllMovementCredits(){
        return movementCreditRepository.findAll();
    }
    public Mono<MovementCredit> getMovementCreditById(String id){
        return movementCreditRepository.findById(id);
    }
    public Mono<MovementCredit> createMovementCredit(MovementCredit movementCredit){
        return movementCreditRepository.save(movementCredit);
    }
    public Mono<MovementCredit> updateMovementCredit(String id, MovementCredit movementCredit){
        return movementCreditRepository.findById(id)
                .flatMap(bean ->{
            bean.setAmount(movementCredit.getAmount());
            bean.setDateStart(movementCredit.getDateStart());
            bean.setDateLimit(movementCredit.getDateLimit());
            bean.setCommission(movementCredit.getCommission());
            bean.setDescription(movementCredit.getDescription());
            bean.setIdCredit(movementCredit.getIdCredit());
            return movementCreditRepository.save(bean);
        });
    }
    public Mono<MovementCredit> deleteMovementCredit(String id){
        return movementCreditRepository.findById(id)
                .flatMap(existMovementCredit -> movementCreditRepository.delete(existMovementCredit)
                        .then(Mono.just(existMovementCredit)));
    }

}
