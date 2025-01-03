package com.keles.discord.Controller;

import com.keles.discord.Service.ChatService;
import com.keles.discord.Service.UserService;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    private final String realpassword = "*****************";

    @PostMapping("/createUser/{password}")
    public String createUser(@RequestBody User user ,@PathVariable("password") String password){
        if (realpassword.equals(password)) {
            userService.createUser(user);
            return "Successful";
        }else {
            return "Wrong Password";
        }
    }


    @DeleteMapping("/deleteUser/{password}")
    public String deleteUser(@RequestBody User user,@PathVariable("password") String password){
        if (realpassword.equals(password)) {
            userService.deleteUser(user.getUsername());
            return "Successful";
        }else {
            return "Wrong Password";
        }
    }
    @PutMapping("/changename/{newname}")
    public String changename(@RequestBody User user,@PathVariable("newname") String newname){
        if(!userService.checkuser(user)){
            return "This user does not exist please first sign in or log in ";
        }
        if(newname.equals(user.getUsername())){
            return "This usernames is equal choose a different user name";
        }else {
            userService.changename(user,newname);
            return "Successful";
        }
    }
    @PutMapping("/changepassword/{newpassword}")
    public String changePassword(@RequestBody User user,@PathVariable("newpassword") String newpassword){
        if(!userService.checkuser(user)){
            return "This user does not exist please first sign in or log in ";
        }
        if(newpassword.equals(user.getPassword())){
            return "This password is equal choose a different password";
        }else {
            userService.changepassword(user,newpassword);
            return "Successful";
        }
    }

    @GetMapping("/showuserchats")
    public List<String>  showuserchats(@RequestBody User user){
        if(!userService.checkuser(user)){
            return Collections.singletonList("This user does not exist please first sign in or log in ");
        }

        return chatService.showUserChat(user);


    }

    @GetMapping("showAllUser/{password}")
    public List<String> showAllUser(@PathVariable("password") String password ){
        if(realpassword.equals(password)){
            return userService.getAllUser();
        }else {
            return List.of("Password is Wrong");
        }
    }
}
