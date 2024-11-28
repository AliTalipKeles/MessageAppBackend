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

    public void deleteUser(String username) {
        repo.delete(repo.findByUsername(username));
    }

    public void changename(User user, String newname) {
        System.out.println(user.getId());
        repo.updateUsernameById(newname,user.getUsername());

    }

    public boolean checkuser(User user) {
        System.out.println();
        User isvalid = repo.findByUsername(user.getUsername());
        if(isvalid == null){
            return false;
        }
        return user.getUsername().equals(isvalid.getUsername()) && user.getPassword().equals(isvalid.getPassword());
    }
}
