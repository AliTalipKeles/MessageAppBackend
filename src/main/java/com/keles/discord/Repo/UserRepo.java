package com.keles.discord.Repo;

import com.keles.discord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Transactional
    @Query(value = "Select * From users Where username = :user_name",nativeQuery = true)
    User findByUsername(@Param("user_name") String username);

    @Transactional
    @Query(value = "SELECT * FROM users where id = :user_id ",nativeQuery = true)
    User findById(@Param("user_id") long user_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.username = :newname WHERE u.username = :realusername",nativeQuery = true)
    void updateUsernameByUsername(@Param("newname") String newname, @Param("realusername") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.password = :newpassword WHERE u.username = :realusername",nativeQuery = true)
    void updatePasswordByUsername(@Param("newpassword") String newpassword,@Param("realusername") String username);
}
