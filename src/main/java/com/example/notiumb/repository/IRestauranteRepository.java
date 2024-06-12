package com.example.notiumb.repository;

import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestauranteRepository extends JpaRepository<Restaurante , Integer> {

    Restaurante findTopByUserEquals(User user);

    Restaurante findTopById(Integer id);

    Restaurante findByCif(String cif);

    @Query(nativeQuery = true, value = "SELECT r.* FROM notium.restaurante r\n" +
            "WHERE r.id_usuario = :id and activo = true;")
    Restaurante findByIdUser(Integer id);

    Restaurante findByUserId(Integer id);

    Restaurante findByIdAndActivoIsTrue(Integer id);

    @Query(nativeQuery = true, value = "SELECT r.* FROM notium.restaurante r\n" +
            "JOIN notium.restaurante_clase rc ON r.id = rc.id_restaurante\n" +
            "WHERE rc.id_clase = :id and activo = true;")
    List<Restaurante> findPorClase(Integer id);

    @Query(value="select r.* from notium.comentario c join notium.restaurante r on c.id_restaurante = r.id where c.activo = TRUE group by r.id order by avg(c.valoracion) DESC",nativeQuery = true)
    List<Restaurante> rankingRestaurantes();

    @Query(value="select r.* from notium.comentario c join notium.restaurante r on c.id_restaurante = r.id where c.activo = TRUE group by r.id order by count(c.valoracion) DESC",nativeQuery = true)
    List<Restaurante> restaurantesMasValorados();

}
