import { Injectable } from '@angular/core';
import { Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicDTO } from '../dto/comic.dto';
import { CompraDTO } from '../dto/compra.dto';
import { ClienteDTO } from '../dto/cliente.dto';

/**
 * Servicio encargado de llamar a los servicios REST de
 * ejemplo
 */
@Injectable({
  providedIn: 'root'
})
export class EjemploService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient: HttpClient) {
    super(injector);
  }


  public consultarComics(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics');
  }

  public crearComic(comicDTO: ComicDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarComic/crear', comicDTO);
  }
  public eliminarComic(idComic: any): Observable<any> {
      return this.httpClient
   .post(`http://localhost:8085/semillero-servicios/rest/GestionarComic/eliminar?idComic=${idComic}`, null );
  }

  public editarComic(idComic: any, nombre: any): Observable<any> {
    return this.httpClient
  .post(`http://localhost:8085/semillero-servicios/rest/GestionarComic/modificar?idComic=${idComic}&nombre=${nombre}`,
   null );
}

public crearCompras(comicDTO: CompraDTO): Observable<any> {
  return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarCompra/crearCompra', comicDTO);
}

public crearClientes(clienteDTO: ClienteDTO): Observable<any> {
  return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarClientes/consultarClientes', clienteDTO);
}


}
