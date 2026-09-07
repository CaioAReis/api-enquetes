package com.areis.enquetes.service;

import com.areis.enquetes.model.Option;
import com.areis.enquetes.repository.OptionRepo;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class OptionServiceImpl implements IOptionService {

  private final OptionRepo optionRepo;

  public OptionServiceImpl(OptionRepo optionRepo) {
    this.optionRepo = optionRepo;
  }

  @Override
  @Transactional
  public Option vote(UUID optionId) {
    Option existingOption = optionRepo.findById(optionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Option not found"));

    optionRepo.incrementVote(optionId);

    existingOption.setVotes(existingOption.getVotes() + 1);

    return existingOption;
  }
}
