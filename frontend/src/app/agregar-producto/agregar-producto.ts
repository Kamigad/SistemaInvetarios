import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router, RouterLink} from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-agregar-producto',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './agregar-producto.html',
  styleUrl: './agregar-producto.css',
})
export class AgregarProducto {
  productoForm = new FormGroup({
    descripcion: new FormControl('', [Validators.required]),
    precio: new FormControl(0, [Validators.required, Validators.min(0)]),
    existencias: new FormControl(0, [Validators.required, Validators.min(0)]),
  });
  private router = inject(Router);
  private productoServicio = inject(ProductoService);

  onSubmit() {
    this.agregarProducto();
  }

  agregarProducto(){
    this.productoServicio.agregarProducto(this.productoForm.value as Producto).subscribe({
      next: (datos) => {
        this.irListaProductos();
      },
      error: (error) => {console.error(error);}
    });
  }

  irListaProductos() {
    this.router.navigate(['/productos']);
  }
}
