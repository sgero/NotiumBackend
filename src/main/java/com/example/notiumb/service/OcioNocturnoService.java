package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserOcioNocturnoDTO;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IDireccionRepository;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findAll());
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
            ocioNuevo.setTelefono(userOcioNocturnoDTO.getTelefono());
            ocioNuevo.setUser(usuario);
            ocioNuevo.setActivo(true);
            ocioNuevo.setVerificado(false);
            ocioNuevo.setImagenMarca(userOcioNocturnoDTO.getImagenMarca());
            ocioNuevo.setDireccion(direccion);

            //envio de emails de verificacion
            emailService.enviarEmailVerificacionOcioNocturno(ocioNocturnoMapper.toDTO(ocioNuevo));
            emailService.enviarEmailVerificacion(userOcioNocturnoDTO.getEmail(), "CLIENTE");

            ocioNocturnoRepository.save(ocioNuevo);

            return ocioNocturnoMapper.toDTO(ocioNuevo);

        }else{

            OcioNocturno ocioModificar = ocioNocturnoRepository.findById(userOcioNocturnoDTO.getId()).orElse(null);

            if (ocioModificar==null){

                return null;

            }else{

                User usuario = ocioModificar.getUser();
                usuario.setUsername(userOcioNocturnoDTO.getUsername());
                usuario.setEmail(userOcioNocturnoDTO.getEmail());

                userRepository.save(usuario);

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
                ocioModificar.setTelefono(userOcioNocturnoDTO.getTelefono());
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

    public String verificarOcioNocturno(OcioNocturnoDTO ocioNocturnoDTO){

        OcioNocturno ocioNocturno = ocioNocturnoRepository.findTopById(ocioNocturnoDTO.getId());
        ocioNocturno.setVerificado(true);
        ocioNocturnoRepository.save(ocioNocturno);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Ocio Nocturno verificado.");

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"Error al procesar la solicitud.\"}";
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
