package com.keles.discord.model;

import com.keles.discord.Repo.UserRepo;
import lombok.Data;

@Data
public class BasicSentence {
    User user;

    String string;

    public BasicSentence(int user_id, String string, UserRepo userRepo){
            this.user =userRepo.getReferenceById(user_id);
            this.string = string;

    }

}
