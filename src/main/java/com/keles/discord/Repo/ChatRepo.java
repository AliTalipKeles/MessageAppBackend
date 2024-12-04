package com.keles.discord.Repo;

import com.keles.discord.model.Chat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat,Integer> {

    Chat findByChatName(String chatName);

    @Transactional
    @Modifying
    @Query(value = "SELECT c.chat_name FROM chats c JOIN chat_participants cp ON c.id = cp.chat_id WHERE cp.user_id = :userid ",nativeQuery = true)
    List<String> getUserChat(@Param("userid") String userid);

    @Transactional
    @Query(value = "SELECT s.user_id,s.string FROM sentence AS s WHERE s.chat_id = :chatid",nativeQuery = true )
    List<Object[]> showchat(@Param("chatid") String chatid);

    @Transactional
    @Query(value = "SELECT c.leader_id FROM chats AS c WHERE c.chat_name = :chatName ",nativeQuery = true)
    Integer getChatLeader(@Param("chatName") String chatName);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO chat_participants VALUES( :chat_id , :user_id)",nativeQuery = true)
    void AddUserInChat(@Param("chat_id") String chatId,@Param("user_id") String userId);

    @Transactional
    @Query(value = "SELECT cp.user_id FROM chats c JOIN chat_participants cp ON c.id = cp.chat_id WHERE cp.chat_id = :chat_id",nativeQuery = true)
    List<String> showChatUser(@Param("chat_id") String chatId);

    @Transactional
    @Query(value = "DELETE FROM chat_participants AS cp WHERE cp.user_id = :userId AND cp.chat_id = :chatId",nativeQuery = true)
    @Modifying
    void deleteUserInChat(@Param("chatId") String chatId,@Param("userId") String userId);
}
