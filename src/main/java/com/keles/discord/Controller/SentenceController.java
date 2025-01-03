package com.keles.discord.Controller;

import com.keles.discord.Repo.UserRepo;
import com.keles.discord.Service.SentenceService;
import com.keles.discord.Service.UserService;
import com.keles.discord.model.User;
import com.keles.discord.model.UserMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SentenceController {
    @Autowired
    UserService userService;

    @Autowired
    SentenceService sentenceService;

    @PostMapping("sendmessage/{chatname}")
    public String sendMessage(@PathVariable("chatname") String chatname, @RequestBody UserMessageRequest request){
        User user = request.getUser();
        String message = request.getMessage();

        if(!userService.checkuser(user)){
            return "This user does not exist please first sign in or log in ";
        }else if(!sentenceService.IsUserInChat(user,chatname)){
            return "This user is not in "+chatname;
        }

        //We should check is User in this chat
        sentenceService.sendMessage(chatname,user,message);
        return "Successful";
    }
}
