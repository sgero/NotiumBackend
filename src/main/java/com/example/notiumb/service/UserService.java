package com.example.notiumb.service;


import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserMapper iUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findTopByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public UserDTO getByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findTopByUsername(username).orElse(null);

        if (user!=null){
            return iUserMapper.toDTO(user);
        }else{
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }

    }

    public UserDTO save(UserDTO userDTO){
        return iUserMapper.toDTO(userRepository.save(iUserMapper.toEntity(userDTO)));
    }

    public Boolean existByCredentials(String username, String password){
        User user = userRepository.findTopByUsername(username).orElse(null);
        return user != null  && passwordEncoder.matches(password,user.getPassword());
    }

    public Boolean existsByUsernameAndPassword(String username, String password){
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public List<UserDTO> getAllByUsernameAndPassword(String username, String password){
        return iUserMapper.toDTO(userRepository.getAllByUsernameAndPassword(username,password));
    }
}
