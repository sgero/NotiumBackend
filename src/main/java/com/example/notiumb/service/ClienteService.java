package com.example.notiumb.service;

import com.example.notiumb.converter.IClienteMapper;
import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IClienteRepository;
import com.example.notiumb.repository.IDireccionRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDireccionRepository direccionRepository;

    @Autowired
    private IClienteMapper converter;

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IDireccionMapper direccionMapper;

    @Autowired
    private AuthController authController;

    @Autowired
    private EmailServiceImpl emailService;

    @Transactional
    public ClienteDTO crearYModificarCliente(UserClienteDTO userClienteDTO) {

        if (userClienteDTO.getId()==null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userClienteDTO.getUsername());
            userDTO.setEmail(userClienteDTO.getEmail());
            userDTO.setPassword(userClienteDTO.getPassword());
            userDTO.setRol(userClienteDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Direccion direccionNueva = new Direccion();
            direccionNueva.setCalle(userClienteDTO.getDireccionDTO().getCalle());
            direccionNueva.setNumero(userClienteDTO.getDireccionDTO().getNumero());
            direccionNueva.setPuerta(userClienteDTO.getDireccionDTO().getPuerta());
            direccionNueva.setCodigoPostal(userClienteDTO.getDireccionDTO().getCodigoPostal());
            direccionNueva.setCiudad(userClienteDTO.getDireccionDTO().getCiudad());
            direccionNueva.setProvincia(userClienteDTO.getDireccionDTO().getProvincia());
            direccionNueva.setPais(userClienteDTO.getDireccionDTO().getPais());
            direccionRepository.save(direccionNueva);

            Direccion direccion = direccionRepository.findTopByCalleOrderByIdDesc(userClienteDTO.getDireccionDTO().getCalle());

            Cliente cliente = new Cliente();

            cliente.setNombre(userClienteDTO.getNombre());
            cliente.setApellidos(userClienteDTO.getApellidos());
            cliente.setDni(userClienteDTO.getDni());
            cliente.setFechaNacimiento(userClienteDTO.getFecha_nacimiento());
            cliente.setTelefono(userClienteDTO.getTelefono());
            cliente.setUser(usuario);
            cliente.setDireccion(direccion);

            //Enviamos email de verificacion al cliente que se ha registrado
            emailService.enviarEmailVerificacion(usuario.getEmail(), "CLIENTE");

            repository.save(cliente);
            return converter.toDTO(cliente);

        }else {

            Cliente clienteModificar = repository.findByIdAndActivoIsTrue(userClienteDTO.getId()).orElse(null);

            if (clienteModificar == null) {

                return null;

            } else {

                Direccion direccionModificada = clienteModificar.getDireccion();

                direccionModificada.setCalle(userClienteDTO.getDireccionDTO().getCalle());
                direccionModificada.setNumero(userClienteDTO.getDireccionDTO().getNumero());
                direccionModificada.setPuerta(userClienteDTO.getDireccionDTO().getPuerta());
                direccionModificada.setCodigoPostal(userClienteDTO.getDireccionDTO().getCodigoPostal());
                direccionModificada.setCiudad(userClienteDTO.getDireccionDTO().getCiudad());
                direccionModificada.setProvincia(userClienteDTO.getDireccionDTO().getProvincia());
                direccionModificada.setPais(userClienteDTO.getDireccionDTO().getPais());

                direccionRepository.save(direccionModificada);

                clienteModificar.setNombre(userClienteDTO.getNombre());
                clienteModificar.setApellidos(userClienteDTO.getApellidos());
                clienteModificar.setDni(userClienteDTO.getDni());
                clienteModificar.setFechaNacimiento(userClienteDTO.getFecha_nacimiento());
                clienteModificar.setTelefono(userClienteDTO.getTelefono());
                clienteModificar.setDireccion(direccionModificada);

                repository.save(clienteModificar);
                return converter.toDTO(clienteModificar);

            }

        }

    }

    public ClienteDTO getByUserId(Integer id) {
        return converter.toDTO(repository.findByIdUser(id));
    }
}
