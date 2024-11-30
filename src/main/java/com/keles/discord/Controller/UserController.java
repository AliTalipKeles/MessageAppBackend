package com.keles.discord.Controller;

import com.keles.discord.Service.UserService;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private final String realpassword = "Alitkeles61_";

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
}
