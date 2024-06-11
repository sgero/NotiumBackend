package com.example.notiumb.repository;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query(value = "select count(c.codigo_reserva) from notium.comentario c where c.id_restaurante = :id_restaurante and c.codigo_reserva = %:codigoReserva%", nativeQuery = true)
    Integer conteoCodigoReserva(Integer id_restaurante, String codigoReserva);

    @Query(value = "select count(c.id) from notium.comentario c where c.id_ocio_nocturno = :id_ocio and c.codigo_reserva = %:codigoReserva%", nativeQuery = true)
    Integer conteoCodigoReservaOcio(Integer id_ocio, String codigoReserva);

    @Query(value = "select c.valoracion from notium.comentario c where c.id_restaurante = :id_restaurante", nativeQuery = true)
    List<Integer> valorcionesRestaurante(Integer id_restaurante);

    @Query(value="select r.* from notium.comentario c join notium.restaurante r on c.id_restaurante = r.id where c.activo = TRUE group by r.id order by avg(c.valoracion) DESC limit 3",nativeQuery = true)
    List<Integer> rankingRestaurantes();

    @Query(value="select c.id_ocio_nocturno from notium.comentario c where c.activo = TRUE group by c.id_ocio_nocturno order by avg(c.valoracion) DESC limit 3",nativeQuery = true)
    List<Integer> rankingOcioNocturno();

    List<Comentario> findAllByRestauranteIdAndActivoIsTrue(Integer id);

    @Query(value="select c.codigo_reserva from notium.comentario c where c.id_restaurante = :id_restaurante",nativeQuery = true)
    List<String> codigoRestaurante(Integer id_restaurante);


    @Query(value="select rr.id_cliente from notium.reserva_restaurante rr where rr.codigo_reserva = :cr",nativeQuery = true)
    Integer IdClienteReserva(String cr);

    List<Comentario> findAllByOcioIdAndActivoIsTrue (Integer id);


}
