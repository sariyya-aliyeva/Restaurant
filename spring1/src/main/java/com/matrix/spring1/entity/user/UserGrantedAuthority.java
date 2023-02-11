package com.matrix.spring1.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_granted_authority")
public class UserGrantedAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user;
}
