import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-editar-producto',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './editar-producto.html',
  styleUrl: './editar-producto.css',
})
export class EditarProducto {

  productoForm = new FormGroup({
    idProducto: new FormControl(0),
    descripcion: new FormControl('', [Validators.required]),
    precio: new FormControl(0, [Validators.required, Validators.min(0)]),
    existencias: new FormControl(0, [Validators.required, Validators.min(0)]),
  });

  producto: Producto = {
    idProducto: 0,
    descripcion: '',
    precio: 0,
    existencias: 0,
  };
  id!: number;

  private productoServicio = inject(ProductoService);
  private ruta = inject(ActivatedRoute);
  private enrutador = inject(Router);

  ngOnInit() {
    this.ruta.params.subscribe(params => {
      this.id = params['id'];
      this.productoServicio.obtenerProductoPorId(this.id).subscribe({
        next: (datos) => {
          this.producto = datos;
          this.productoForm.patchValue({
            idProducto: this.producto.idProducto,
            descripcion: this.producto.descripcion,
            precio: this.producto.precio,
            existencias: this.producto.existencias,
          });
        },
        error: (errores) => console.error('Error al obtener el producto:', errores)
      });
    });
  }

  onSubmit() {
    //editar producto
    this.guardarProducto();
  }

  guardarProducto() {
    this.productoServicio.editarProducto(this.id, this.productoForm.value as Producto).subscribe({
      next: (datos) => this.irProductoLista(),
      error: (errores) => console.error("Error al editar el producto: " + errores)
    });
  }

  irProductoLista() {
    this.enrutador.navigate(['/productos']);
  }
}