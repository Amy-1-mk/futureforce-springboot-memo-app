package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "姓を入力してください")
    @Column(nullable = false, length = 255)
    private String lastName;

    @NotBlank(message = "名を入力してください")
    @Column(nullable = false, length = 255)
    private String firstName;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式で入力してください")
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "パスワードを入力してください")
    @Column(nullable = false, length = 255)
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}