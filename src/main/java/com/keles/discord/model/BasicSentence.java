package com.keles.discord.model;

import com.keles.discord.Repo.UserRepo;
import lombok.Data;

@Data
public class BasicSentence {
    Long userid;
    String string;
    public BasicSentence(Long userid, String string){
            this.userid=userid ;
            this.string = string;

    }

}
