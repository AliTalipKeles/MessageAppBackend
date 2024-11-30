package com.keles.discord.Service;

import com.keles.discord.Repo.ChatRepo;
import com.keles.discord.Repo.UserRepo;
import com.keles.discord.model.BasicSentence;
import com.keles.discord.model.Chat;
import com.keles.discord.model.Sentence;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    public List<String> showuserchat(User user) {

        return repo.getUserChat(String.valueOf(userRepo.findByUsername(user.getUsername()).getId()));

    }

    public List<BasicSentence> showchat(String chatname, User user){
        //if(repo.isuserinchat()){ this should implemented
            List<Object[]>  rawResults = repo.showchat(String.valueOf(repo.findByChatName(chatname).getId()));
            List<BasicSentence> basicSentences = new ArrayList<>();
            System.out.println(basicSentences);
            return rawResults.stream()
                    .map(row-> new BasicSentence((Long) row[0],(String) row[1]))
                    .collect(Collectors.toList());

        //}
    }

    public String printchat(List<BasicSentence> basicSentences) {
        StringBuilder stringBuffer = new StringBuilder();
        for(BasicSentence s : basicSentences){
            stringBuffer.append(userRepo.findById(s.getUserid()).getUsername()).append(": \n").append(s.getString()).append("\n");
        }
        return stringBuffer.toString();

    }
}
