package com.example.notiumb.service;

import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcioNocturnoService {

    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;

    @Autowired
    private EmailServiceImpl emailService;

    public List<OcioNocturnoDTO> getAll() {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findAllByActivoIsTrue());
    }

    public OcioNocturno getById(@Param("id") Integer id) {
        return ocioNocturnoRepository.findByIdAndActivoIsTrue(id).orElse(null);
    }
    public OcioNocturnoDTO save(OcioNocturnoDTO ocioNocturnoDTO) throws MessagingException {
        if (ocioNocturnoDTO.getId()==null){
            OcioNocturnoDTO ocioNuevo = new OcioNocturnoDTO();
            ocioNuevo.setNombre(ocioNocturnoDTO.getNombre());
            ocioNuevo.setCif(ocioNocturnoDTO.getCif());
            ocioNuevo.setHoraApertura(ocioNocturnoDTO.getHoraApertura());
            ocioNuevo.setHoraCierre(ocioNocturnoDTO.getHoraCierre());
            ocioNuevo.setAforo(ocioNocturnoDTO.getAforo());
            ocioNuevo.setImagenMarca(ocioNocturnoDTO.getImagenMarca());

            //envio de email de verificacion
            emailService.enviarEmailVerificacionOcioNocturno(ocioNuevo);

            ocioNocturnoRepository.save(ocioNocturnoMapper.toEntity(ocioNuevo));
            return ocioNuevo;
        }else{
            OcioNocturno ocioModificar = ocioNocturnoRepository.findById(ocioNocturnoDTO.getId()).orElse(null);
            if (ocioModificar==null){
                return null;
            }else{
                ocioModificar.setNombre(ocioNocturnoDTO.getNombre());
                ocioModificar.setCif(ocioNocturnoDTO.getCif());
                ocioModificar.setHoraApertura(ocioNocturnoDTO.getHoraApertura());
                ocioModificar.setHoraCierre(ocioNocturnoDTO.getHoraCierre());
                ocioModificar.setAforo(ocioNocturnoDTO.getAforo());
                ocioModificar.setImagenMarca(ocioNocturnoDTO.getImagenMarca());
                ocioNocturnoRepository.save(ocioModificar);
                return ocioNocturnoMapper.toDTO(ocioModificar);
            }
        }
    }

    public void delete(Integer id){

        OcioNocturno ocioAEliminar = ocioNocturnoRepository.findById(id).orElse(null);

        if (ocioAEliminar!=null){
            ocioAEliminar.setActivo(false);
            ocioNocturnoRepository.save(ocioAEliminar);
        }
    }

    public OcioNocturno getOcioNocturnoByCif(String cif){
        return ocioNocturnoRepository.findByCif(cif);
    }

    public void actualizarOcioNocturno(OcioNocturno ocioNocturno) {
        ocioNocturnoRepository.save(ocioNocturno);
    }
}
