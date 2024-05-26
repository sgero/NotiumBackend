package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.converter.IRppMapper;
import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.model.ListaOcio;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.repository.IListaOcioRepository;
import com.example.notiumb.repository.IRppRepository;
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
    private IOcioNocturnoMapper ocioNocturnoMapper;

    public List<RppDTO> getAll(){return converter.toDTO(repository.findAllByActivoIsTrue());}

    public List<RppDTO> getAllByOcio(@Param("id") Integer idOcio){return converter.toDTO(repository.findAllByOcioNocturnoIdAndActivoIsTrue(idOcio));}

    public RppDTO getById(@Param("id") Integer id){
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElse(null));
    }

    public RppDTO save(RppDTO rppDTO){
        if (rppDTO.getId()==null){
            Rpp newRpp = Rpp.builder()
                    .nombre(rppDTO.getNombre())
                    .apellidos(rppDTO.getApellidos())
                    .dni(rppDTO.getDni())
                    .telefono(rppDTO.getTelefono())
                    .fechaNacimiento(rppDTO.getFechaNacimiento())
                    .user(userMapper.toEntity(rppDTO.getUserDTO()))
                    .direccion(direccionMapper.toEntity(rppDTO.getDireccionDTO()))
                    .ocioNocturno(ocioNocturnoMapper.toEntity(rppDTO.getOcioNocturnoDTO()))
                    .build();
            repository.save(newRpp);
            return converter.toDTO(newRpp);
        }
        else {
            Rpp rppModify = repository.findByIdAndActivoIsTrue(rppDTO.getId()).orElse(null);
            if (rppModify==null){
                return null;
            }else {
                rppModify.setNombre(rppDTO.getNombre());
                rppModify.setApellidos(rppDTO.getApellidos());
                rppModify.setDni(rppDTO.getDni());
                rppModify.setTelefono(rppDTO.getTelefono());
                rppModify.setFechaNacimiento(rppDTO.getFechaNacimiento());
                rppModify.setUser(userMapper.toEntity(rppDTO.getUserDTO()));
                rppModify.setDireccion(direccionMapper.toEntity(rppDTO.getDireccionDTO()));
                rppModify.setOcioNocturno(ocioNocturnoMapper.toEntity(rppDTO.getOcioNocturnoDTO()));
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
