package com.sparta.week01.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        private String refreshToken;

        @Column(nullable = false)
        private String username;

        public RefreshToken(String refreshToken, String username) {
                this.refreshToken = refreshToken;
                this.username = username;
        }
}
