package com.keles.discord.Controller;

import com.keles.discord.Service.ChatService;
import com.keles.discord.Service.UserService;
import com.keles.discord.model.BasicSentence;
import com.keles.discord.model.Sentence;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/showuserchats")
    public List<String>  showuserchats(@RequestBody User user){
        service.checkuser(user);

        return chatService.showuserchat(user);


    }

    @GetMapping("/showchat/{chatname}")
    public String showchat(@PathVariable String chatname, @RequestBody User user){
        if(service.checkuser(user)) {
            List<BasicSentence> basicSentences = chatService.showchat(chatname, user);

            return chatService.printchat(basicSentences);
        }else {
            return "This user does not exist please first sign in or log in ";
        }
    }

}
