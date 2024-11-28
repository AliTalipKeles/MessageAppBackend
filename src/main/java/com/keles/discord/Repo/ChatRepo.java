package com.keles.discord.Repo;

import com.keles.discord.model.Chat;
import com.keles.discord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat,Integer> {

    Chat findByChatName(String chatName);
}
