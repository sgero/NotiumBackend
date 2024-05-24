package com.example.notiumb.repository;


import com.example.notiumb.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;




@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findTopByUsername(String username);
    User findTopByUsernameAndActivoTrue(String username);
    Boolean existsByUsernameAndPassword(String username, String password);

//    @NotNull
    List<User> findAll();

    List<User> getAllByUsernameAndPassword(String username, String password);

    User findTopByTokenVerificacion(String tokenVerificacion);

    User findTopById(Integer id);

    User findTopByEmail(String email);

//    ScopedValue<Object> findTopByToken(String token);
}
