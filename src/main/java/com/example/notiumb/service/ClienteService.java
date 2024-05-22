package com.example.notiumb.service;

import com.example.notiumb.converter.IClienteMapper;
import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IClienteRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private IUserRepository userRepository;

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

    public ClienteDTO crearYModificarCliente(UserClienteDTO userClienteDTO) {

        if (userClienteDTO.getId()==null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userClienteDTO.getUsername());
            userDTO.setEmail(userClienteDTO.getEmail());
            userDTO.setPassword(userClienteDTO.getPassword());
            userDTO.setRol(userClienteDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Cliente cliente = new Cliente();

            cliente.setNombre(userClienteDTO.getNombre());
            cliente.setApellidos(userClienteDTO.getApellidos());
            cliente.setDni(userClienteDTO.getDni());
            cliente.setFechaNacimiento(userClienteDTO.getFechaNacimiento());
            cliente.setUser(usuario);
            cliente.setDireccion(direccionMapper.toEntity(userClienteDTO.getDireccionDTO()));

            //Enviamos email de verificacion al cliente que se ha registrado
            emailService.enviarEmailVerificacion(usuario.getEmail(), "CLIENTE");


            repository.save(cliente);
            return converter.toDTO(cliente);

        }else {

            Cliente clienteModificar = repository.findByIdAndActivoIsTrue(userClienteDTO.getId()).orElse(null);

            if (clienteModificar == null) {

                return null;

            } else {

                clienteModificar.setNombre(userClienteDTO.getNombre());
                clienteModificar.setApellidos(userClienteDTO.getApellidos());
                clienteModificar.setDni(userClienteDTO.getDni());
                clienteModificar.setFechaNacimiento(userClienteDTO.getFechaNacimiento());
                clienteModificar.setDireccion(direccionMapper.toEntity(userClienteDTO.getDireccionDTO()));

                repository.save(clienteModificar);
                return converter.toDTO(clienteModificar);

            }

        }

    }

}
