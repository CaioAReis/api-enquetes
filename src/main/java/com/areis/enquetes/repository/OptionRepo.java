package com.areis.enquetes.repository;

import com.areis.enquetes.model.Option;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OptionRepo extends ListCrudRepository<Option, UUID> {
  @Modifying
  @Query("UPDATE Option o SET o.votes = o.votes + 1 WHERE o.id = :id")
  void incrementVote(@Param("id") UUID id);
}
