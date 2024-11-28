package com.keles.discord.Service;

import com.keles.discord.Repo.ChatRepo;
import com.keles.discord.Repo.SentenceRepo;
import com.keles.discord.Repo.UserRepo;
import com.keles.discord.model.Chat;
import com.keles.discord.model.Sentence;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SentenceService {

    @Autowired
    SentenceRepo sentenceRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ChatRepo chatRepo;

    public void sendMessage(String chatname, User user, String message) {

        Sentence sentence = new Sentence();
        sentence.setAuthor(userRepo.findByUsername(user.getUsername()));
        sentence.setString(message);
        sentence.setChat(chatRepo.findByChatName(chatname));

        sentenceRepo.save(sentence);
    }
}
