package com.example.DOTA.services;

import com.example.DOTA.models.User;
import com.example.DOTA.models.enums.Role;
import com.example.DOTA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user){userRepository.save(user);}
    public List<User> list(){return userRepository.findAll();}
    public User userName(String name){return userRepository.findByName(name);}

    public boolean createUser(User user){
        String nameUser = user.getName();
        if (userName(nameUser) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Сохранение нового пользователя email: {}", nameUser);
        save(user);
        return true;
    }

    public void banUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            if (user.isActive()){
                user.setActive(false);
                log.info("Пользователь забанен id = {}; email{}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Пользователь рабанен id = {}; email = {}", user.getId(), user.getEmail());
            }
        }
        save(user);
        assert user != null;
        log.info("Пользавтель имя = {} сохранен", user.getName());
    }

    public void changeUserRoles(User user, Map<String, String> form){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByName(principal.getName());
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    @Transactional
    public void deleteRole(Long id,String role){
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.getRoles().remove(Role.valueOf(role));
        save(user);
    }

    @Transactional
    public void addRole(Long id, String role){
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.getRoles().add(Role.valueOf(role));
        save(user);
    }

}
