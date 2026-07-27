package gm.inventario.controlador;

import gm.inventario.modelo.Producto;
import gm.inventario.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/productos") //http://localhost:8080/api/productos
@CrossOrigin(value = "http://localhost:4200") // Puerto por default de Angular
public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    private final IProductoServicio iProductoServicio;

    public ProductoControlador(IProductoServicio iProductoServicio){
        this.iProductoServicio = iProductoServicio;
    }

    @GetMapping("/") //http://localhost:8080/api/productos/
    public List<Producto> obtenerProductos(){
        List<Producto> productos = iProductoServicio.listarProducto();
        logger.info("Productos obtenidos: ");
        productos.forEach(producto -> logger.debug(producto.toString()));
        return productos;
    }
}
