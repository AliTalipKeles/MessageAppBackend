package com.keles.discord.Service;

import com.keles.discord.Repo.ChatRepo;
import com.keles.discord.Repo.UserRepo;
import com.keles.discord.model.BasicSentence;
import com.keles.discord.model.Chat;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        chat.setLeader_id(user.getId());
        repo.save(chat);
    }


    public List<String> showUserChat(User user) {

        return repo.getUserChat(String.valueOf(userRepo.findByUsername(user.getUsername()).getId()));

    }

    public List<BasicSentence> showChat(String chatname, User user){
        List<Object[]>  rawResults = repo.showchat(String.valueOf(repo.findByChatName(chatname).getId()));
        List<BasicSentence> basicSentences = new ArrayList<>();
        if(IsUserInChat(user,chatname)){

            System.out.println(basicSentences);
            return rawResults.stream()
                    .map(row-> new BasicSentence((Long) row[0],(String) row[1]))
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }

    public String printChat(List<BasicSentence> basicSentences) {
        StringBuilder stringBuffer = new StringBuilder();
        for(BasicSentence s : basicSentences){
            stringBuffer.append(userRepo.findById(s.getUserid()).getUsername()).append(": \n").append(s.getString()).append("\n");
        }
        return stringBuffer.toString();
    }

    public boolean IsUserInChat(User user,String chatname) {
        List<String> userchats = repo.getUserChat(String.valueOf(userRepo.findByUsername(user.getUsername()).getId()));

        return userchats.contains(chatname);
    }

    public boolean isUserChatLeader(String chatName ,User user) {
        User user1 = userRepo.getReferenceById(repo.getChatLeader(chatName));

        return (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword()));
    }

    public void AddUserInChat(String chatName, String userId) {
        Chat chat = repo.findByChatName(chatName);
        repo.AddUserInChat(String.valueOf(chat.getId()),userId);
    }
    public String deleteUserInChat(String chatname, String userId) {
        repo.deleteUserInChat(String.valueOf(repo.findByChatName(chatname).getId()),userId);
        return "Succesful";
    }

    public List<String> showChatUser(String chatname) {
        return repo.showChatUser(String.valueOf(repo.findByChatName(chatname).getId()));

    }


}
