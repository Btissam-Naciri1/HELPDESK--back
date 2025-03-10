package com.example.User_Auth_service.Service;
import com.example.User_Auth_service.Model.App_user;
import com.example.User_Auth_service.Model.AdminN2;
import com.example.User_Auth_service.Repository.User_Repository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final User_Repository userRepository;

    public UserDetailsServiceImpl(User_Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<App_user> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        App_user user = optionalUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Assign role as authority
        authorities.add(new SimpleGrantedAuthority(user.getTypeUtilisateur().name()));

        // If the user is an Admin N2, assign additional group authorities
        if (user instanceof AdminN2 adminN2) {
            adminN2.getGroups().forEach(group -> {
                authorities.add(new SimpleGrantedAuthority("GROUP_" + group.getNom()));
            });
        }

        // Return UserDetails object with username, password, and authorities
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
