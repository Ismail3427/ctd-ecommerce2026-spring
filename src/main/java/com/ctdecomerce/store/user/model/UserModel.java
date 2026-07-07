package com.ctdecomerce.store.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @Column(unique = true)
    private String userId;

    @Column()
    private String email;

    @Column()
    private String ipAddress;

    @Column()
    private int loginsCount;
}

