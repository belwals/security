package com.github.belwals.security.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.belwals.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    private String name;
}
