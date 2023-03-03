package com.example.service;

import com.example.dto.EvaluateDTO;
import com.example.dto.TransactionDTO;
import com.example.entity.OfferEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.RequestEntity;
import com.example.entity.TransactionEntity;
import com.example.exceptions.AppForbiddenException;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.mapper.TransactionMapper;
import com.example.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionRepository;
    private final ProfileService profileService;
    private final RequestService requestService;
    private final OfferService offerService;

    public String addTransaction(TransactionDTO dto) {
        ProfileEntity profile = profileService
                .getCarrier(dto.getCarrierId());
        RequestEntity request = requestService
                .get(dto.getRequestId());
        OfferEntity offer = offerService
                .get(dto.getOfferId());
        if (profile == null && request == null && offer == null) {
            log.info("Offer or Request or Profile is null "
                    + request + " " + offer + " " + profile);
            throw new AppForbiddenException("profile or request or offer is null");
        }
        Optional<TransactionEntity> optional = transactionRepository.
                findByRequestIdAndOfferId(dto.getRequestId(),
                        dto.getOfferId());
        if (optional.isPresent()) {
            throw new ItemAlreadyExistException("Already exists");
        }
        assert request != null;
        assert offer != null;
        if (!offer.getProductId().equalsIgnoreCase(request.getProductId())) {
            throw new AppForbiddenException("The product id did not arrive");
        }
        TransactionEntity entity = new TransactionEntity();
        entity.setCarrierId(dto.getCarrierId());
        entity.setOfferId(dto.getOfferId());
        entity.setRequestId(dto.getRequestId());

        transactionRepository.save(entity);
        return "Successfully Added Transaction";
    }

    public Boolean evaluateTransaction(EvaluateDTO dto) {
        if (dto.getBal() > 10 || dto.getBal() < 1) {
            return false;
        }
        transactionRepository.updateBal(dto.getTransactionId()
                , dto.getBal());
        return true;
    }

    public List<TransactionMapper> getList() {
        return transactionRepository.getList();
    }
}
