package com.example.notiumb.service;

import com.example.notiumb.converter.IProductoFormatoMapper;
import com.example.notiumb.converter.IProductoMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.model.enums.TipoCategoria;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProductoMapper productoMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private ICartaRestauranteRespository cartaRestauranteRespository;

    @Autowired
    private IProductoFormatoRepository productoFormatoRepository;

    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private ICartaOcioRepository cartaOcioRepository;

    @Autowired
    private IProductoFormatoMapper productoFormatoMapper;

    public ProductoDTO crearProducto(ProductoAuxDTO productoAuxDTO) {

        //String tokensio = productoAuxDTO.getUsername();
        //String username = jwtService.extractUsername(productoDTO.getUsername());
        User user = userRepository.findTopByUsernameAndActivoTrue(productoAuxDTO.getUsername());
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        CartaRestauranteDTO cartaToSet = new CartaRestauranteDTO();
        cartaToSet.setId(carta.getId());
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(productoAuxDTO.getNombre());
        productoDTO.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
        productoDTO.setCartaRes(cartaToSet);
        productoDTO.setActivo(true);


        return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(productoDTO)));

    }

    public List<ListadoProductosDTO> listarProducto(TokenDTO tokenDTO) {
        //String tokensio = tokenDTO.getToken();
        //String username = jwtService.extractUsername(tokenDTO.getToken());
        User user = userRepository.findTopByUsernameAndActivoTrue(tokenDTO.getToken());
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        List<Producto> productos = productoRepository.findByCartaResEqualsAndActivoTrue(carta);
        List<ListadoProductosDTO> listafinal = new ArrayList<>();
        if (carta == null) {
            return listafinal;
        }
            for (Producto p : productos){
                List<ProductoFormatoDTO> formatos = productoFormatoMapper.toDTO(productoFormatoRepository.findByProductoEquals(p));
                ListadoProductosDTO nuevo = new ListadoProductosDTO();
                nuevo.setProducto(productoMapper.toDTO(p));
                nuevo.setFormatos(formatos);
                listafinal.add(nuevo);
            }
            return listafinal;

        }

    public ProductoDTO guardarProducto(ProductoAuxDTO productoAuxDTO,  User user) {

        Producto producto = new Producto();

        if (user.getRol() == Rol.OCIONOCTURNO) {
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);
            producto.setNombre(productoAuxDTO.getNombre());
            producto.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
            producto.setCartaOcio(cartaOcio);
        } else if (user.getRol() == Rol.RESTAURANTE) {
            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
            producto.setNombre(productoAuxDTO.getNombre());
            producto.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
            producto.setCartaRes(cartaRestaurante);
        }

        return productoMapper.toDTO(productoRepository.save(producto));
    }


    public ProductoDTO getById(@Param("id") Integer id) { return productoMapper.toDTO(productoRepository.findTopById(id)); }

    public String BajaAltaProducto(ProductoDTO productoDTO){
        Producto producto = productoRepository.findTopById(productoDTO.getId());
        if (producto.getActivo()){
            producto.setActivo(false);
        } else {
            producto.setActivo(true);
        }
        productoRepository.save(producto);
        return "Borrado lógico";
    }

    public List<ListadoProductosDTO> listarProductoDescartado(TokenDTO tokenDTO) {
        //String tokensio = tokenDTO.getToken();
        //String username = jwtService.extractUsername(tokenDTO.getToken());
        User user = userRepository.findTopByUsernameAndActivoTrue(tokenDTO.getToken());
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        List<Producto> productos = productoRepository.findByCartaResEqualsAndActivoFalse(carta);
        List<ListadoProductosDTO> listafinal = new ArrayList<>();
        for (Producto p : productos) {
            List<ProductoFormatoDTO> formatos = productoFormatoMapper.toDTO(productoFormatoRepository.findByProductoEquals(p));
            ListadoProductosDTO nuevo = new ListadoProductosDTO();
            nuevo.setProducto(productoMapper.toDTO(p));
            nuevo.setFormatos(formatos);
            listafinal.add(nuevo);
        }
        return listafinal;
    }


    public List<ProductoDTO> listarByProducto(User user) {

        List<ProductoDTO> productos = new ArrayList<>();

        if (user.getRol()== Rol.OCIONOCTURNO){
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);

            productos =  productoMapper.toDTO(productoRepository.findByCartaOcioEqualsAndActivoTrue(cartaOcio));

        }else if (user.getRol()== Rol.RESTAURANTE) {
            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);

            productos = productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(cartaRestaurante));
        }
        return productos;
    }

    public String eliminarProducto(ProductoDTO productoDTO){
        Producto producto = productoRepository.findTopById(productoDTO.getId());
        List<ProductoFormato> formatos = productoFormatoRepository.findByProductoEquals(producto);
        productoFormatoRepository.deleteAll(formatos);
        productoRepository.delete(producto);
        return "Producto eliminado";
    }

    public void deleteProductoFormato(@Param("id") Integer id){
        ProductoFormato productoFormato = productoFormatoRepository.findById(id).orElse(null);
        if (productoFormato != null) {
            Producto producto = productoFormato.getProducto();
            List<ProductoFormato> formatos = productoFormatoRepository.findByProductoEquals(producto);
            if (formatos.size() == 1) {
                productoRepository.delete(producto);
            } else {
                productoFormatoRepository.delete(productoFormato);
            }
        }
    }

}
