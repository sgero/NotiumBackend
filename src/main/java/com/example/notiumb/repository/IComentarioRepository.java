package com.example.notiumb.repository;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Comentario;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query(value = "select count(rr.codigo_reserva) from notium.comentario rr where rr.id_restaurante = :id_restaurante and rr.codigo_reserva = %:codigoReserva%", nativeQuery = true)
    Integer conteoCodigoReserva(Integer id_restaurante, String codigoReserva);

    @Query(value = "select c.valoracion from notium.comentario c where c.id_restaurante = :id_restaurante", nativeQuery = true)
    List<Integer> valorcionesRestaurante(Integer id_restaurante);

    @Query(value="select c.id_restaurante from notium.comentario c where c.activo = TRUE group by c.id_restaurante order by avg(c.valoracion) DESC limit 3",nativeQuery = true)
    List<Integer> rankingRestaurantes();

    @Query(value="select c.id_ocio_nocturno from notium.comentario c where c.activo = TRUE group by c.id_ocio_nocturno order by avg(c.valoracion) DESC limit 3",nativeQuery = true)
    List<Integer> rankingOcioNocturno();
}
