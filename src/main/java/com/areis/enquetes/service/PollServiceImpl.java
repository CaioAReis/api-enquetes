package com.areis.enquetes.service;

import com.areis.enquetes.enums.PollStatus;
import com.areis.enquetes.model.Option;
import com.areis.enquetes.model.Poll;
import com.areis.enquetes.repository.OptionRepo;
import com.areis.enquetes.repository.PollRepo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PollServiceImpl implements IPollService {

  private final PollRepo pollRepo;
  private final OptionRepo optionRepo;

  public PollServiceImpl(PollRepo pollRepo, OptionRepo optionRepo) {
    this.pollRepo = pollRepo;
    this.optionRepo = optionRepo;
  }

  @Override
  public Poll create(Poll poll) {
    if (poll.getOptions() != null) {
      poll.getOptions().forEach(option -> {
        option.setPoll(poll);
        option.setVotes(0);
      });
    }
    return pollRepo.save(poll);
  }

  @Override
  public Poll update(UUID id, Poll poll) {
    Poll existingPoll = pollRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found!"));

    existingPoll.setStatus(poll.getStatus());
    existingPoll.setQuestion(poll.getQuestion());

    existingPoll.setEndDate(poll.getEndDate());
    existingPoll.setStartDate(poll.getStartDate());

    return pollRepo.save(existingPoll);
  }

  @Override
  public void delete(UUID id) {
    Poll existingPoll = pollRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found!"));

    pollRepo.deleteById(existingPoll.getId());
  }

  @Override
  public Poll get(UUID id) {
    return pollRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found!"));
  }

  @Override
  public List<Poll> list() {
    return pollRepo.findAll();
  }

  @Override
  public List<Poll> listByStatus(PollStatus status) {
    return pollRepo.findByStatus(status);
  }

  @Override
  public Option addOption(UUID pollId, Option option) {
    Poll existingPoll = pollRepo.findById(pollId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found!"));

    option.setPoll(existingPoll);
    option.setVotes(0);

    return optionRepo.save(option);
  }
}
