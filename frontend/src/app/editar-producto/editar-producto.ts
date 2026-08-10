import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editar-producto',
  imports: [],
  templateUrl: './editar-producto.html',
  styleUrl: './editar-producto.css',
})
export class EditarProducto {

  producto: Producto = {
    idProducto: 0,
    descripcion: '',
    precio: 0,
    existencias: 0,
  };
  id!: number;

  private productoServicio = inject(ProductoService);
  private ruta = inject(ActivatedRoute);

  ngOnInit() {
    this.ruta.params.subscribe(params => {
      this.id = params['id'];
      this.productoServicio.obtenerProductoPorId(this.id).subscribe({
        next: (datos) => this.producto = datos,
        error: (errores) => console.error('Error al obtener el producto:', errores)
      });
    });
  }
}
