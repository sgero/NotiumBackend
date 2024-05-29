package com.example.notiumb.service;

import com.example.notiumb.converter.IPromocionMapper;
import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.Promocion;
import com.example.notiumb.model.enums.TipoPromocion;
import com.example.notiumb.repository.IPromocionRepository;
import com.example.notiumb.utilidades.CodigoRespuestaAPI;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IPromocionMapper promocionMapper;

    public RespuestaDTO verificarCodigo(Integer idPromocion, String codigo) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Promocion promocion = promocionRepository.findByIdAndActivoIsTrue(idPromocion);
        int cantidadADescontar = 0;

        if (promocion != null && promocion.getCodigo() != null ){
            if (codigo.equalsIgnoreCase(promocion.getCodigo())){
                if (promocion.getTipo().equals(TipoPromocion.GRATIS)){
                    cantidadADescontar = 100;
                }else if (promocion.getTipo().equals(TipoPromocion.DOSXUNO)){
                    cantidadADescontar = 50;
                }else if (promocion.getTipo().equals(TipoPromocion.BIENVENIDA_10PORCIENTO)){
                    cantidadADescontar = 10;
                }
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200, cantidadADescontar);
            }else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200, cantidadADescontar);
            }
        }
        return respuestaDTO;
    }

    public List<PromocionDTO> getActivas() {
        return promocionMapper.toDTO(promocionRepository.findAllByActivoIsTrue());
    }
}
