package com.example.notiumb.thread;

import com.example.notiumb.converter.IListaOcioMapper;
import com.example.notiumb.model.ListaOcio;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.repository.IListaOcioRepository;
import com.example.notiumb.repository.IRppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ListasOcioThread {

    private final IListaOcioRepository listaOcioRepository;
    private final IRppRepository rppRepository;

    @Async
    @Transactional
    public void run(Integer idRppOriginal, Integer idRppDestino){
        Rpp rppOriginal = rppRepository.findByIdAndActivoIsTrue(idRppOriginal).orElse(null);
        Rpp rppDestino = rppRepository.findByIdAndActivoIsTrue(idRppDestino).orElse(null);
        Set<ListaOcio> listas = rppOriginal.getListasOcio();
        listas.forEach(l->{
            l.setRpp(rppDestino);
        });
        listaOcioRepository.saveAll(listas);
    }

}
