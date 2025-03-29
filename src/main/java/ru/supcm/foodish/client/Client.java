package ru.supcm.foodish.client;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
@RequiredArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
}
