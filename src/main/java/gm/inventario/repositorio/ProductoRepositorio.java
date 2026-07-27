package gm.inventario.repositorio;

import gm.inventario.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Integer> {
}
