package com.example.notiumb.repository;

import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestauranteRepository extends JpaRepository<Restaurante , Integer> {

    Restaurante findTopByUserEquals(User user);

    Restaurante findTopById(Integer id);


}
