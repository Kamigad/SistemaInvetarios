package gm.inventario.excepcion;

public class ExistenciasInvalidasExcepcion extends RuntimeException{
    public ExistenciasInvalidasExcepcion(String mensaje){
        super(mensaje);
    }
}
