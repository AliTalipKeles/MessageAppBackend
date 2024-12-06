package com.keles.discord.Service;

import com.keles.discord.Repo.UserRepo;
import com.keles.discord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepo repo;

    public void createUser(User user) {
        repo.save(user);
    }

    public String  deleteUser(String username) {
        User user = repo.findByUsername(username);
        System.out.println(repo.findByUsername(username));
        if(user == null){
            return "this user do not exist";
        }

        repo.deleteById(Math.toIntExact(repo.findByUsername(username).getId()));
        return "Successful";
    }

    public void changename(User user, String newname) {
        repo.updateUsernameByUsername(newname,user.getUsername());

    }

    public boolean checkuser(User user) {
        System.out.println();
        User isvalid = repo.findByUsername(user.getUsername());
        if(isvalid == null){
            return false;
        }
        return user.getUsername().equals(isvalid.getUsername()) && user.getPassword().equals(isvalid.getPassword());
    }

    public void changepassword(User user, String newpassword) {
        repo.updatePasswordByUsername(newpassword,user.getUsername());
    }
}
