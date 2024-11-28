package com.keles.discord.Service;

import com.keles.discord.Repo.ChatRepo;
import com.keles.discord.Repo.UserRepo;
import com.keles.discord.model.Chat;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepo repo;
    @Autowired
    UserRepo userRepo;

    public void createChat(User user, List<Integer> usersid,String chatname) {
        usersid.add(Math.toIntExact(userRepo.findByUsername(user.getUsername()).getId()));
        Chat chat = new Chat();
        chat.setChatName(chatname);
        chat.setParticipants(userRepo.findAllById(usersid));
        repo.save(chat);
    }
}
