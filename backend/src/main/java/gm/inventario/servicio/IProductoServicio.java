package gm.inventario.servicio;

import gm.inventario.modelo.Producto;

import java.util.List;

public interface IProductoServicio {

    List<Producto> listarProducto();
    Producto buscarProductoId(Integer idProducto);
    Producto guardarProducto(Producto producto);
    void eliminarProductoId(Integer idProducto);

}
