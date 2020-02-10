import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { ConsultarComicComponent } from './consultarComic/consultarComic-component';
import { ComprasComponent} from './semillero/componentes/compras/compras.component';
import { ClientesComponent } from './semillero/componentes/clientes/clientes.component';


const routes: Routes = [
  { path: 'gestionar-comic', component: GestionarComicComponent },
  { path: 'bienvenida', component: BienvenidaComponent, data : null },
  { path: 'consultarComic', component: ConsultarComicComponent},
  { path: 'gestionar-compra', component: ComprasComponent },
  { path: 'gestionar-cliente', component: ClientesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
