package com.example.notiumb.service;

import com.example.notiumb.converter.IMesaMapper;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.dto.MesaDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.IMesaRepository;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.ULogger;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private IMesaRepository imesaRepository;
    @Autowired
    private IMesaMapper mesaMapper;
    @Autowired
    private IRestauranteRepository iRestauranteRepository;


    public List<MesaDTO> listadoMesas(){
        List<Mesa> mesas = imesaRepository.findAll();
        return mesaMapper.toDTO(mesas);
    }

    public MesaDTO mesaID(Integer id){
        Mesa mesas = imesaRepository.findById(id).orElse(null);
        return mesaMapper.toDTO(mesas);
    }


    public RespuestaDTO mesaRestaurante(MesaDTO mesaDTO) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Integer numeroPlazasCubierta = imesaRepository.numeroDePlazaPorMesasRestaurante(mesaDTO.getRestauranteDTO().getId());

        try {
            Mesa nuevaMesa = new Mesa();
                if(mesaDTO.getNum_plazas()!=0 && (numeroPlazasCubierta + mesaDTO.getNum_plazas()) < 100/*Aqui iría variable aforo*/) {
                    nuevaMesa.setNum_plazas(mesaDTO.getNum_plazas());
                    nuevaMesa.setReservada(mesaDTO.getReservada());
                    Restaurante restaurantesMesa = iRestauranteRepository.findTopById(mesaDTO.getRestauranteDTO().getId());
                    nuevaMesa.setRestaurante(restaurantesMesa);

                    imesaRepository.save(nuevaMesa);
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_MESA_CREADA);
                }else{
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_MESA_NO_CREADA);
                }
        } catch (Exception e) {UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_MESA_NO_CREADA);}

        return respuestaDTO;
    }



    public RespuestaDTO eliminarMesa(Integer id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Mesa mesaDesactivar = imesaRepository.findById(id).orElse(null);

        try {
            if(mesaDesactivar!=null && mesaDesactivar.getActivo()!=false ) {
            mesaDesactivar.setActivo(false);
            imesaRepository.save(mesaDesactivar);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_MESA_DESACTIVADA);
            }else{
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_NO_DESACTIVADA);

            }
        } catch (Exception e) {UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_NO_DESACTIVADA);}

        return respuestaDTO;
    }






}
