package com.github.belwals.security.user;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    private LocalDateTime validateDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
