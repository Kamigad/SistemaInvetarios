package gm.inventario.servicio;

import gm.inventario.excepcion.ExistenciasInvalidasExcepcion;
import gm.inventario.modelo.Producto;
import gm.inventario.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoServicio implements IProductoServicio{

    private final ProductoRepositorio productoRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio){
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public List<Producto> listarProducto() {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoId(Integer idProducto) {
        return this.productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        if(producto.getExistencias() < 0){
            throw  new ExistenciasInvalidasExcepcion("Las Existencias no pueden ser negativas");
        }
        return this.productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProductoId(Integer idProducto) {
        this.productoRepositorio.deleteById(idProducto);
    }
}
