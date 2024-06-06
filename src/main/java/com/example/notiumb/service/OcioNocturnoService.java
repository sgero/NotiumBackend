package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserOcioNocturnoDTO;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IDireccionRepository;
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

    @Autowired
    private IDireccionRepository direccionRepository;

    public List<OcioNocturnoDTO> getAll() {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findAllByActivoIsTrue());
    }

    public OcioNocturnoDTO getById(@Param("id") Integer id) {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findByIdAndActivoIsTrue(id).orElse(null));
    }
    public OcioNocturnoDTO getByEventoId(Integer id) {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findByIdEvento(id));
    }
    @Transactional
    public OcioNocturnoDTO crearYModificarOcioNocturno(UserOcioNocturnoDTO userOcioNocturnoDTO) throws MessagingException {

        if (userOcioNocturnoDTO.getId()==null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userOcioNocturnoDTO.getUsername());
            userDTO.setEmail(userOcioNocturnoDTO.getEmail());
            userDTO.setPassword(userOcioNocturnoDTO.getPassword());
            userDTO.setRol(userOcioNocturnoDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Direccion direccionNueva = new Direccion();
            direccionNueva.setCalle(userOcioNocturnoDTO.getDireccionDTO().getCalle());
            direccionNueva.setNumero(userOcioNocturnoDTO.getDireccionDTO().getNumero());
            direccionNueva.setPuerta(userOcioNocturnoDTO.getDireccionDTO().getPuerta());
            direccionNueva.setCodigoPostal(userOcioNocturnoDTO.getDireccionDTO().getCodigoPostal());
            direccionNueva.setCiudad(userOcioNocturnoDTO.getDireccionDTO().getCiudad());
            direccionNueva.setProvincia(userOcioNocturnoDTO.getDireccionDTO().getProvincia());
            direccionNueva.setPais(userOcioNocturnoDTO.getDireccionDTO().getPais());
            direccionRepository.save(direccionNueva);

            Direccion direccion = direccionRepository.findTopByCalleOrderByIdDesc(userOcioNocturnoDTO.getDireccionDTO().getCalle());

            OcioNocturno ocioNuevo = new OcioNocturno();

            ocioNuevo.setNombre(userOcioNocturnoDTO.getNombre());
            ocioNuevo.setCif(userOcioNocturnoDTO.getCif());
            ocioNuevo.setHoraApertura(userOcioNocturnoDTO.getHoraApertura());
            ocioNuevo.setHoraCierre(userOcioNocturnoDTO.getHoraCierre());
            ocioNuevo.setAforo(userOcioNocturnoDTO.getAforo());
            ocioNuevo.setUser(usuario);
            ocioNuevo.setImagenMarca(userOcioNocturnoDTO.getImagenMarca());
            ocioNuevo.setDireccion(direccion);

            //envio de email de verificacion
            emailService.enviarEmailVerificacionOcioNocturno(ocioNocturnoMapper.toDTO(ocioNuevo));
            ocioNocturnoRepository.save(ocioNuevo);

            return ocioNocturnoMapper.toDTO(ocioNuevo);

        }else{

            OcioNocturno ocioModificar = ocioNocturnoRepository.findById(userOcioNocturnoDTO.getId()).orElse(null);

            if (ocioModificar==null){

                return null;

            }else{

                Direccion direccionModificada = ocioModificar.getDireccion();

                direccionModificada.setCalle(userOcioNocturnoDTO.getDireccionDTO().getCalle());
                direccionModificada.setNumero(userOcioNocturnoDTO.getDireccionDTO().getNumero());
                direccionModificada.setPuerta(userOcioNocturnoDTO.getDireccionDTO().getPuerta());
                direccionModificada.setCodigoPostal(userOcioNocturnoDTO.getDireccionDTO().getCodigoPostal());
                direccionModificada.setCiudad(userOcioNocturnoDTO.getDireccionDTO().getCiudad());
                direccionModificada.setProvincia(userOcioNocturnoDTO.getDireccionDTO().getProvincia());
                direccionModificada.setPais(userOcioNocturnoDTO.getDireccionDTO().getPais());

                direccionRepository.save(direccionModificada);

                ocioModificar.setNombre(userOcioNocturnoDTO.getNombre());
                ocioModificar.setCif(userOcioNocturnoDTO.getCif());
                ocioModificar.setHoraApertura(userOcioNocturnoDTO.getHoraApertura());
                ocioModificar.setHoraCierre(userOcioNocturnoDTO.getHoraCierre());
                ocioModificar.setAforo(userOcioNocturnoDTO.getAforo());
                ocioModificar.setImagenMarca(userOcioNocturnoDTO.getImagenMarca());
                ocioModificar.setDireccion(direccionModificada);


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

    public OcioNocturnoDTO getByUserId(Integer id) {
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findByIdUser(id));
    }


}
