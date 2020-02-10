import { Component, OnInit } from '@angular/core';
import { ClienteDTO } from '../../dto/cliente.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EjemploService } from '../../services/ejemplo.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

      /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarClienteForm: FormGroup;


    /**
     * Atributo que contendra la informacion de la compra
     */
    public cliente: ClienteDTO;

    /**
     * Atributo que contendra la lista de las compras creadas
     */
    public listaCliente: Array<ClienteDTO>;


    public idCliente: number ;

    /**
     * Atributo para indicar que se quiere modificar un registro
     */
    public banderaEditar: boolean;
    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;


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
    private gestionarClienteService: EjemploService) {
      this.gestionarClienteForm = this.fb.group({
        nombre : [null, Validators.required],
        identificacion : [null, Validators.required],
        fechaNacimiento : [null, Validators.required]

      });
    }

  ngOnInit() {
  }


  public crearCliente(): void {


    this.submitted = true;
    if (this.gestionarClienteForm.invalid) {
        return;
    }

    this.cliente = new ClienteDTO();
    this.cliente.nombre = this.gestionarClienteForm.controls.nombre.value;
    this.cliente.identidad = this.gestionarClienteForm.controls.identificacion.value;
    this.cliente.fechaNacimiento = this.gestionarClienteForm.controls.fechaNacimiento.value;



        this.gestionarClienteService.crearClientes(this.cliente).subscribe(resultado => {
            this.mensajeExito = resultado.mensajeEjecucion;
            this.bool_mensajeExito = true;

        }, error => {
            console.log('Ocurrio un error al momento de crear el cliente');
        });

      this.limpiarFormulario();

}

private limpiarFormulario(): void {
  this.submitted = false;
  this.gestionarClienteForm.controls.nombre.setValue(null);
  this.gestionarClienteForm.controls.identificacion.setValue(null);
  this.gestionarClienteForm.controls.fechaNacimiento.setValue(null);
}

get f() {
  return this.gestionarClienteForm.controls;
}

}
