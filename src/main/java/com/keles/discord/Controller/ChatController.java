package com.keles.discord.Controller;

import com.keles.discord.Service.ChatService;
import com.keles.discord.Service.UserService;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
