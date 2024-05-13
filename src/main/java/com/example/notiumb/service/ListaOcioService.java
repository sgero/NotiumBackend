package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IListaOcioMapper;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.ListaOcio;
import com.example.notiumb.repository.IEventoRepository;
import com.example.notiumb.repository.IListaOcioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaOcioService {

    @Autowired
    private IListaOcioRepository repository;
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IListaOcioMapper converter;
    @Autowired
    private IEventoMapper eventoMapper;

    public List<ListaOcioDTO> getAll(){ return converter.toDTO(repository.findAllByActivoIsTrue()); }

    public ListaOcioDTO getById(@Param("id") Integer id) {
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElse(null));
    }

    public List<ListaOcioDTO> getAllByEventoId(@Param("id") Integer id) {
        return converter.toDTO(repository.findListaOcioByEventoIdAndActivoIsTrue(id));
    }

    public ListaOcioDTO actualizarEstadoListas(@Param("idEvento") Integer idEvento, @Param("idLista") Integer idLista) {
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        ListaOcio listaActivar = repository.findById(idLista).orElse(null);

        if (evento != null && listaActivar != null){
            List<ListaOcio> listaOcios = repository.findAllByEventoId(idEvento);
            for (ListaOcio l : listaOcios){
                l.setActivo(l.getId().equals(listaActivar.getId()));
            }
            repository.saveAll(listaOcios);
        }
        return converter.toDTO(repository.findListaOcioByEventoIdAndActivoIsTrue(idEvento));
    }

    public void delete(@Param("id") Integer id){

        ListaOcio listaAEliminar = repository.findByIdAndActivoIsTrue(id).orElse(null);

        if (listaAEliminar!=null) {
            listaAEliminar.setActivo(false);
            repository.save(listaAEliminar);
        }
    }

}
