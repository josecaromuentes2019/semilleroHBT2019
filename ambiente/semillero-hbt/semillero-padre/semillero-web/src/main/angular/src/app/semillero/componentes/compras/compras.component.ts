import { Component, OnInit } from '@angular/core';
import { CompraDTO } from '../../dto/compra.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EjemploService } from '../../services/ejemplo.service';


@Component({
  selector: 'app-compras',
  templateUrl: './compras.component.html',
  styleUrls: ['./compras.component.css']
})
export class ComprasComponent implements OnInit {


    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarCompraForm: FormGroup;

    /**
     * Atributo para tomar el indice del registro a modificar
     */
    public indiceActualizar: number;

    /**
     * Atributo que contendra la informacion de la compra
     */
    public compra: CompraDTO;

    /**
     * Atributo que contendra la lista de las compras creadas
     */
    public listaCompra: Array<CompraDTO>;


    public idCompra: number ;

    /**
     * Atributo para indicar que se quiere modificar un registro
     */
    public banderaEditar: boolean;
    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * Atributo para mostrar div de  elementoeliminado
     */
    public eliminado: boolean;

    /**
     * Atributo para guardar nombre de elemento eliminado
     */
    public nombreEliminado: string;

    /**
     *
     *
     * @param variable para poner mensaje de creacion de compra
     */

     public mensajeExito: string;

    /**
     * variable para mostrar div mensaje exito
     */
     public bool_mensajeExito: boolean;

    /**
     * @description Este es el constructor del componente CompraComponent
     * @author jose caro <josecaro0187@gmail.com>
     */

  constructor(private fb: FormBuilder,
    private router: Router,
    private gestionarCompraService: EjemploService) {

      this.gestionarCompraForm = this.fb.group({
        nombre : [null],
        fechaCompra : [null, Validators.required],
        idCliente : [null, Validators.required],
        idComic : [null, Validators.required]
    });

     }

  ngOnInit() {


  }

  public crearCompra(): void {

    this.eliminado = false;
    this.submitted = true;
    if (this.gestionarCompraForm.invalid) {
        return;
    }

    this.compra = new CompraDTO();
    this.compra.nombre = this.gestionarCompraForm.controls.nombre.value;
    this.compra.idCliente = this.gestionarCompraForm.controls.idCliente.value;
    this.compra.fechaCompra = this.gestionarCompraForm.controls.fechaCompra.value;
    this.compra.idComic = this.gestionarCompraForm.controls.idComic.value;



        this.gestionarCompraService.crearCompras(this.compra).subscribe(resultado => {
            this.mensajeExito = resultado.mensajeEjecucion;
            this.bool_mensajeExito = true;

        }, error => {
            console.log('Ocurrio un error al momento de crear la compra');
        });

      this.limpiarFormulario();

}

private limpiarFormulario(): void {
  this.submitted = false;
  this.gestionarCompraForm.controls.nombre.setValue(null);
  this.gestionarCompraForm.controls.idCliente.setValue(null);
  this.gestionarCompraForm.controls.fechaCompra.setValue(null);
  this.gestionarCompraForm.controls.idComic.setValue(null);
}

get f() {
  return this.gestionarCompraForm.controls;
}


}
