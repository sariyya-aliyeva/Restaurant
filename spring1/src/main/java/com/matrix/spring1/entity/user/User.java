package com.matrix.spring1.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_detail")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked = false;

    private boolean credentialsNonExpired;

    private boolean enabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserGrantedAuthority> authorities = new HashSet<>();

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
    }

    public User() {

    }

    public void addAuthority(UserGrantedAuthority authority) {
        this.authorities.add(authority);
        authority.setUser(this);
    }

}
