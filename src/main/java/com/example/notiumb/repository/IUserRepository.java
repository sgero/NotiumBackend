package com.example.notiumb.repository;


import com.example.notiumb.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;




@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    User findTopByUsername(String username);
    User findTopByUsernameAndActivoTrue(String username);

//    @NotNull
    List<User> findAll();

    User findTopByTokenVerificacion(String tokenVerificacion);

    User findTopById(Integer id);

    User findTopByEmail(String email);

}
