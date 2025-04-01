package de.rxbsi.streamnest.user.service;

import de.rxbsi.streamnest.storage.video.service.VideoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "STREAM_USER")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "admin")
    private boolean admin;

    @OneToMany(mappedBy = "user")
    private List<VideoEntity> videos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(this.admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
