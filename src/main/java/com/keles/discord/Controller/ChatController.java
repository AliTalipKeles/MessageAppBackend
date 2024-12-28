package com.keles.discord.Controller;

import com.keles.discord.Service.ChatService;
import com.keles.discord.Service.UserService;
import com.keles.discord.model.BasicSentence;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    UserService service;

    @PostMapping("/createChat/{usersid}/{chatname}")
    public String createChat(@RequestBody User user, @PathVariable List<Integer> usersid,@PathVariable String chatname){
        if(!service.checkuser(user)){
            return "This user does not exist please first sign in or log in ";
        }
        chatService.createChat(user,usersid,chatname);
        return "Successful";
    }


    @GetMapping("/showchat/{chatname}")
    public String showchat(@PathVariable String chatname, @RequestBody User user) {

        if (service.checkuser(user)) {
            List<BasicSentence> basicSentences = chatService.showChat(chatname, user);
            if (basicSentences == null){
                return "This user is not in the "+ chatname;
            }
            return chatService.printChat(basicSentences);
        }else {
            return "This user does not exist please first sign in or log in ";
        }
    }

    @PutMapping("adduserinchat/{chatName}/{user_id}")
    public String addUserInChat(@RequestBody User user,@PathVariable String chatName,@PathVariable String user_id){
        if(chatService.isUserChatLeader(chatName,user)){
            chatService.AddUserInChat(chatName,user_id);
            return "Succesful";
        }
        return "You are not chat leader";
    }

    @GetMapping("showChatUser/{chatname}")
    public List<String> showChatUser(@RequestBody User user,@PathVariable String chatname){
        if(!service.checkuser(user)){
            return Collections.singletonList("This user does not exist please first sign in or log in ");
        }
        if(chatService.IsUserInChat(user,chatname)){
            return chatService.showChatUser(chatname);
        }
        return Collections.singletonList("You are not in chat");
    }

    @DeleteMapping("deleteUserInChat/{chatName}/{user_id}")
    public String deleteUserInChat(@RequestBody User user,@PathVariable String chatName,@PathVariable String user_id){
        if(!service.checkuser(user)){
            return "This user does not exist please first sign in or log in ";
        }
        if(chatService.isUserChatLeader(chatName,user)){
            return chatService.deleteUserInChat(chatName,user_id);
        }
        return "You are not chat leader";

    }

}
