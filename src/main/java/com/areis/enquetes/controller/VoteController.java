package com.areis.enquetes.controller;

import com.areis.enquetes.model.Option;
import com.areis.enquetes.service.OptionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

  private final OptionServiceImpl optionService;
  private final SimpMessagingTemplate messagingTemplate;

  public VoteController(OptionServiceImpl optionService, SimpMessagingTemplate messagingTemplate) {
    this.optionService = optionService;
    this.messagingTemplate = messagingTemplate;
  }

  @PostMapping("/{optionId}")
  public ResponseEntity<Void> vote(@PathVariable UUID optionId) {
    Option updatedOption = optionService.vote(optionId);

    messagingTemplate.convertAndSend("/topic/polls/" + updatedOption.getPoll().getId(), updatedOption);

    return ResponseEntity.ok().build();
  }
}
