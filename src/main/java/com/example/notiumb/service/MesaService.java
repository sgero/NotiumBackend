package com.example.notiumb.service;

import com.example.notiumb.converter.IMesaMapper;
import com.example.notiumb.dto.ReservaMesasDTO;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.dto.MesaDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.IMesaRepository;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
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

    public List<MesaDTO> listadoMesasByID(int id){
        List<Mesa> mesas = imesaRepository.findAllByRestaurante_Id(id);
        return mesaMapper.toDTO(mesas);
    }

    public MesaDTO mesaID(Integer id){
        Mesa mesas = imesaRepository.findById(id).orElse(null);
        return mesaMapper.toDTO(mesas);
    }


    public RespuestaDTO mesaRestaurante(MesaDTO mesaDTO) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {
            Mesa nuevaMesa = new Mesa();
                if(mesaDTO.getNumPlazas()!=0 ) {
                    nuevaMesa.setNumPlazas(mesaDTO.getNumPlazas());
                    nuevaMesa.setReservada(false);
                    nuevaMesa.setActivo(true);
                    Restaurante restaurantesMesa = iRestauranteRepository.findTopById(mesaDTO.getRestauranteDTO().getId());
                    nuevaMesa.setRestaurante(restaurantesMesa);

                    imesaRepository.save(nuevaMesa);
                    Mesa mesa = nuevaMesa;
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
