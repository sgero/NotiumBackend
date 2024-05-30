package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserOcioNocturnoDTO;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
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
    private AuthController authController;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDireccionMapper direccionMapper;

    @Autowired
    private EmailServiceImpl emailService;

    public List<OcioNocturnoDTO> getAll() {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findAllByActivoIsTrue());
    }

    public OcioNocturno getById(@Param("id") Integer id) {
        return ocioNocturnoRepository.findByIdAndActivoIsTrue(id).orElse(null);
    }

    @Transactional
    public OcioNocturnoDTO crearYModificarOcioNocturno(UserOcioNocturnoDTO UserOcioNocturnoDTO) throws MessagingException {

        if (UserOcioNocturnoDTO.getId()==null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(UserOcioNocturnoDTO.getUsername());
            userDTO.setEmail(UserOcioNocturnoDTO.getEmail());
            userDTO.setPassword(UserOcioNocturnoDTO.getPassword());
            userDTO.setRol(UserOcioNocturnoDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            OcioNocturno ocioNuevo = new OcioNocturno();

            ocioNuevo.setNombre(UserOcioNocturnoDTO.getNombre());
            ocioNuevo.setCif(UserOcioNocturnoDTO.getCif());
            ocioNuevo.setHoraApertura(UserOcioNocturnoDTO.getHoraApertura());
            ocioNuevo.setHoraCierre(UserOcioNocturnoDTO.getHoraCierre());
            ocioNuevo.setAforo(UserOcioNocturnoDTO.getAforo());
            ocioNuevo.setUser(usuario);
            ocioNuevo.setImagenMarca(UserOcioNocturnoDTO.getImagenMarca());
            ocioNuevo.setDireccion(direccionMapper.toEntity(UserOcioNocturnoDTO.getDireccionDTO()));

            //envio de email de verificacion
            emailService.enviarEmailVerificacionOcioNocturno(ocioNocturnoMapper.toDTO(ocioNuevo));
            ocioNocturnoRepository.save(ocioNuevo);

            return ocioNocturnoMapper.toDTO(ocioNuevo);

        }else{

            OcioNocturno ocioModificar = ocioNocturnoRepository.findById(UserOcioNocturnoDTO.getId()).orElse(null);

            if (ocioModificar==null){

                return null;

            }else{

                ocioModificar.setNombre(UserOcioNocturnoDTO.getNombre());
                ocioModificar.setCif(UserOcioNocturnoDTO.getCif());
                ocioModificar.setHoraApertura(UserOcioNocturnoDTO.getHoraApertura());
                ocioModificar.setHoraCierre(UserOcioNocturnoDTO.getHoraCierre());
                ocioModificar.setAforo(UserOcioNocturnoDTO.getAforo());
                ocioModificar.setImagenMarca(UserOcioNocturnoDTO.getImagenMarca());
                ocioModificar.setDireccion(direccionMapper.toEntity(UserOcioNocturnoDTO.getDireccionDTO()));


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
