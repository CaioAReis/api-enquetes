package com.areis.enquetes.service;

import com.areis.enquetes.enums.PollStatus;
import com.areis.enquetes.model.Option;
import com.areis.enquetes.model.Poll;

import java.util.List;
import java.util.UUID;

public interface IPollService {
  public Poll create(Poll poll);
  public Poll update(UUID id, Poll poll);
  public void delete(UUID id);
  public List<Poll> list();
  public List<Poll> listByStatus(PollStatus status);

  public Option addOption(UUID pollId, Option option);
}
