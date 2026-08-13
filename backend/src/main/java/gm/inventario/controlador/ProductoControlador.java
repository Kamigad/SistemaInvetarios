package gm.inventario.controlador;

import gm.inventario.modelo.Producto;
import gm.inventario.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PostMapping("/")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: " + producto);
        return this.iProductoServicio.guardarProducto(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(
            @PathVariable Integer id
    ){
        Producto producto = this.iProductoServicio.buscarProductoId(id);
        logger.info("Producto a editar: " + producto);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto productoRecibido){

        Producto producto = this.iProductoServicio.buscarProductoId(id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencias(productoRecibido.getExistencias());
        //guardar la informacion
        logger.info("Producto editado: " + producto);
        this.iProductoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Integer id){
        logger.info("Producto eliminado con id: " + id);
        this.iProductoServicio.eliminarProductoId(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}