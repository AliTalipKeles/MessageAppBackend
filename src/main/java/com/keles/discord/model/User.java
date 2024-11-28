package com.keles.discord.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "participants")  // mappedBy, Chat modelindeki participants'a işaret etmeli
    private List<Chat> chats = new ArrayList<>();  // Kullanıcının katıldığı sohbetler

    @OneToMany(mappedBy = "author")
    private List<Sentence> sentences = new ArrayList<>();  // Kullanıcının yazdığı mesajlar

}
