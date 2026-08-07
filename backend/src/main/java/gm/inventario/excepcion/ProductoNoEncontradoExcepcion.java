package gm.inventario.excepcion;

public class ProductoNoEncontradoExcepcion extends RuntimeException {
    public ProductoNoEncontradoExcepcion(String message) {
        super(message);
    }
}
