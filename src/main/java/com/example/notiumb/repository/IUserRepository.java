package com.example.notiumb.repository;


import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    User findTopByUsername(String username);
    User findTopByUsernameAndActivoTrue(String username);

//    @NotNull
    List<User> findAll();

    List<User> getAllByUsernameAndPassword(String username, String password);

    User findTopByTokenVerificacion(String tokenVerificacion);

    User findTopById(Integer id);

    User findTopByEmail(String email);

//    ScopedValue<Object> findTopByToken(String token);
}
