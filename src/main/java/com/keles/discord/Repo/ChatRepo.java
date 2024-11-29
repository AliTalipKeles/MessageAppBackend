package com.keles.discord.Repo;

import com.keles.discord.model.BasicSentence;
import com.keles.discord.model.Chat;
import com.keles.discord.model.Sentence;
import com.keles.discord.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

public interface ChatRepo extends JpaRepository<Chat,Integer> {

    Chat findByChatName(String chatName);

    @Transactional
    @Modifying
    @Query(value = "SELECT DISTINCT chats.chat_name FROM chats JOIN chat_participants WHERE chat_participants.user_id = :userid ",nativeQuery = true)
    List<String> getUserChat(@Param("userid") String userid);

    @Transactional
    @Query(value = "SELECT s.user_id,s.string FROM sentence AS s WHERE s.chat_id = 1",nativeQuery = true )
    List<Object[]> showchat(@Param("chatid") String chatid);
}
