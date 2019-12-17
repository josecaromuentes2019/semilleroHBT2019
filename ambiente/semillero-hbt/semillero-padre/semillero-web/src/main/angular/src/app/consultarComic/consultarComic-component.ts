import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'consultarComic',
    templateUrl: './consultarComic-component.html'
})

export class ConsultarComicComponent implements OnInit {


    public gestionarComicForm: FormGroup;
 /**
  *
  * @description Metodo constructor que recibe inyeccion de Router para hacer routing
  * recibe tambien de FormBuilder para formularios reactivos
  * y recibe inyeccion de ActivitedRoutr para recibir parametros
  */
    constructor(private router: Router, private activeroute: ActivatedRoute, private fb: FormBuilder) {

        this.gestionarComicForm = this.fb.group({
            nombre : [null, Validators.required],
            editorial : [null],
            tematica : [null],
            coleccion : [null],
            numeroPaginas : [null],
            precio : [null],
            autores : [null],
            color : [null]
        });
    }

    /**
     * @description este metodo se ejecuta despues del constructos
     */

    ngOnInit(): void {

        let comic = this.activeroute.snapshot.params;
        this.consultarComic(comic);
    }

    /**
     *
     * @description Metodo para cunsultar loc Comic
     */

          public consultarComic(comic: any): void {
            this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
            this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
            this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
            this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
            this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
            this.gestionarComicForm.controls.precio.setValue(comic.precio);
            this.gestionarComicForm.controls.autores.setValue(comic.autores);
            this.gestionarComicForm.controls.color.setValue(comic.color);
            this.gestionarComicForm.controls.nombre.disable();
            this.gestionarComicForm.controls.editorial.disable();
            this.gestionarComicForm.controls.tematica.disable();
            this.gestionarComicForm.controls.coleccion.disable();
            this.gestionarComicForm.controls.numeroPaginas.disable();
            this.gestionarComicForm.controls.precio.disable();
            this.gestionarComicForm.controls.autores.disable();
            this.gestionarComicForm.controls.color.disable();

        }

        /**
         * @description Metodo para usar paginado o hacer routing
         */
        public navegarGestionarComic(): void {
            this.router.navigate(['gestionar-comic']);
          }

}
