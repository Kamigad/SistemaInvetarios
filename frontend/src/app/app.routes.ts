import { Routes } from '@angular/router';
import { ProductoLista } from './producto-lista/producto-lista';

//http://localhost:4200/productos
export const routes: Routes = [
    {path: 'productos', component: ProductoLista},
    {path: '', redirectTo: 'productos', pathMatch: 'full'}
];
