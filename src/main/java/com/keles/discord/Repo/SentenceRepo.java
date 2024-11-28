package com.keles.discord.Repo;

import com.keles.discord.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepo extends JpaRepository<Sentence,Integer> {
}
