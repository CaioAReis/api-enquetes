package com.areis.enquetes.controller;

import com.areis.enquetes.enums.PollStatus;
import com.areis.enquetes.model.Option;
import com.areis.enquetes.model.Poll;
import com.areis.enquetes.service.PollServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/polls")
public class PollController {

  private final PollServiceImpl service;

  public PollController(PollServiceImpl service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Poll> create(@RequestBody Poll poll) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(poll));
  }

  @GetMapping
  public ResponseEntity<List<Poll>> list(@RequestParam(required = false) PollStatus status) {
    if (status != null) return ResponseEntity.ok(service.listByStatus(status));

    return ResponseEntity.ok(service.list());
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@RequestParam UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<Poll> update(@RequestParam UUID id, @RequestBody Poll poll) {
    return ResponseEntity.ok(service.update(id, poll));
  }

  @PatchMapping
  public ResponseEntity<Option> addOption(@RequestParam UUID pollId, @RequestBody Option option) {
    return ResponseEntity.ok(service.addOption(pollId, option));
  }
}
