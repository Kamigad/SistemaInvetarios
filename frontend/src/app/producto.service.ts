import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Producto } from './producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {

  private urlBase = 'http://localhost:8080/api/productos/';
  private clienteHttp = inject(HttpClient);

  obtenerProductosLista(): Observable<Producto[]> {
    return this.clienteHttp.get<Producto[]>(this.urlBase);
  }

  agregarProducto(producto: Producto): Observable<Producto>{
    return this.clienteHttp.post<Producto>(this.urlBase, producto);
  }
  
}
