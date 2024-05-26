package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.converter.IRppMapper;
import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserRppDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import com.example.notiumb.security.auth.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RppService {

    @Autowired
    private IRppRepository repository;
    @Autowired
    private IListaOcioRepository listaOcioRepository;
    @Autowired
    private IRppMapper converter;
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IDireccionMapper direccionMapper;
    @Autowired
    private IDireccionRepository direccionRepository;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private AuthController authController;

    @Autowired
    private IUserRepository userRepository;

    public List<RppDTO> getAll(){return converter.toDTO(repository.findAllByActivoIsTrue());}

    public List<RppDTO> getAllByOcio(@Param("id") Integer idOcio){return converter.toDTO(repository.findAllByOcioNocturnoIdAndActivoIsTrue(idOcio));}

    public RppDTO getById(@Param("id") Integer id){
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElse(null));
    }

    public RppDTO saveRpp(@Param("id") Integer id, UserRppDTO userRppDTO){
        if (userRppDTO.getId()==null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userRppDTO.getUserDTO().getUsername());
            userDTO.setEmail(userRppDTO.getUserDTO().getEmail());
            userDTO.setPassword(userRppDTO.getUserDTO().getPassword());
            userDTO.setRol(userRppDTO.getUserDTO().getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Direccion direccion = direccionMapper.toEntity(userRppDTO.getDireccionDTO());
            direccionRepository.save(direccion);

            OcioNocturno ocioNocturno= ocioNocturnoRepository.findByIdAndActivoIsTrue(id).orElse(null);

            Rpp newRpp = Rpp.builder()
                    .nombre(userRppDTO.getNombre())
                    .apellidos(userRppDTO.getApellidos())
                    .dni(userRppDTO.getDni())
                    .telefono(userRppDTO.getTelefono())
                    .fechaNacimiento(userRppDTO.getFechaNacimiento())
                    .user(usuario)
                    .direccion(direccion)
                    .ocioNocturno(ocioNocturno)
                    .activo(true)
                    .build();
            repository.save(newRpp);
            return converter.toDTO(newRpp);
        }
        else {
            Rpp rppModify = repository.findByIdAndActivoIsTrue(userRppDTO.getId()).orElse(null);
            if (rppModify==null){
                return null;
            }else {
                rppModify.setNombre(userRppDTO.getNombre());
                rppModify.setApellidos(userRppDTO.getApellidos());
                rppModify.setDni(userRppDTO.getDni());
                rppModify.setTelefono(userRppDTO.getTelefono());
                rppModify.setFechaNacimiento(userRppDTO.getFechaNacimiento());
                rppModify.setDireccion(direccionMapper.toEntity(userRppDTO.getDireccionDTO()));
                repository.save(rppModify);
                return converter.toDTO(rppModify);
            }
        }
    }

    public void delete(@Param("id") Integer id){
        Rpp deleteRpp = repository.findByIdAndActivoIsTrue(id).orElse(null);
        List<ListaOcio> listasRpp = listaOcioRepository.findAllByRppIdAndActivoIsTrue(id);

        if (deleteRpp!=null){
            deleteRpp.setActivo(false);
            repository.save(deleteRpp);
            if (listasRpp!=null){
                listasRpp.stream().forEach(l->l.setActivo(false));
                listaOcioRepository.saveAll(listasRpp);
            }

        }
    }
}
