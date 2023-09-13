package com.wellsfargo.training.ims.service;

import com.wellsfargo.training.ims.model.Dealer;
import com.wellsfargo.training.ims.repository.DealerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class DealerService {
    @Autowired
    private DealerRepository dealerRepository;
    public Dealer registerDealer(Dealer d){
        return dealerRepository.save(d);
    }
    public Optional<Dealer> loginDealer(String email)
    {
        return dealerRepository.findByEmail(email);
    }

}
