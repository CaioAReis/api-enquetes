package com.areis.enquetes.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "options")
public class Option {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "poll_id")
  private Poll poll;

  @Column(nullable = false)
  private String text;

  @Column(nullable = false)
  private Integer votes = 0;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Poll getPoll() {
    return poll;
  }

  public void setPoll(Poll poll) {
    this.poll = poll;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getVotes() {
    return votes;
  }

  public void setVotes(Integer votes) {
    this.votes = votes;
  }
}
