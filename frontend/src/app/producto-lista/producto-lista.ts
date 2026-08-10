import { Component, inject, signal } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  imports: [],
  templateUrl: './producto-lista.html',
  styleUrl: './producto-lista.css',
})
export class ProductoLista {
  productos = signal<Producto[]>([]);

  private productoServicio = inject(ProductoService);
  private enrutador = inject(Router);

  ngOnInit() {
    //Cargar los productos
    this.obtenerProductos();
  }

  private obtenerProductos(): void {
    this.productoServicio.obtenerProductosLista().subscribe(
      {
        next: (datos) => {
          this.productos.set(datos);
        },
        error: (error) => {
          console.error('Error al obtener los productos:', error);
        }
      }
    );
  }

  editarProducto(id: number): void {
    this.enrutador.navigate(['editar-producto', id]);
  }

}
