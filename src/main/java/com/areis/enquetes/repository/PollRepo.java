package com.areis.enquetes.repository;

import com.areis.enquetes.enums.PollStatus;
import com.areis.enquetes.model.Poll;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface PollRepo extends ListCrudRepository<Poll, UUID> {
  public List<Poll> findByStatus(PollStatus status);
}
