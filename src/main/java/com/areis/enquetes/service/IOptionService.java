package com.areis.enquetes.service;

import com.areis.enquetes.model.Option;

import java.util.UUID;

public interface IOptionService {
  public Option vote(UUID optionId);
}
