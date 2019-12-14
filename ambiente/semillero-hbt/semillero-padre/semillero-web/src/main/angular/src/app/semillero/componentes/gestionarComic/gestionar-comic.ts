
import { ComicDTO } from '../../dto/comic.dto';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 *
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    // tslint:disable-next-line:component-selector
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})
export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm: FormGroup;

    /**
     * Atributo para tomar el indice del registro a modificar
     */
    public indiceActualizar: number;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array<ComicDTO>;


    public idComic: number ;

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
     * @description Este es el constructor del componente GestionarComicComponent
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    constructor(private fb: FormBuilder,
        private router: Router) {
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
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    ngOnInit(): void {
        this.idComic =  0;
        console.log('Ingreso al al evento oninit');
        this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actulizar un comic
     */
    public crearActualizarComic(): void {

        this.eliminado = false;
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
        this.idComic++;
        this.comic = new ComicDTO();
        this.comic.id = this.idComic + '';
        this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
        this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
        this.comic.tematica = this.gestionarComicForm.controls.tematica.value;
        this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
        this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
        this.comic.precio = this.gestionarComicForm.controls.precio.value;
        this.comic.autores = this.gestionarComicForm.controls.autores.value;
        this.comic.color = this.gestionarComicForm.controls.color.value;

        if ( this.banderaEditar ) {

            let mismaPosicion = this.indiceActualizar;
            mismaPosicion++;
            this.comic.id = (mismaPosicion) + '';
            this.listaComics[this.indiceActualizar] = this.comic;
            this.banderaEditar = false;
        } else {

        this.listaComics.push(this.comic);
        }
        this.limpiarFormulario();

    }


    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComic(posicion: number): void {
        this.indiceActualizar = posicion;
        let comic = this.listaComics[posicion];
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre.enable();
        this.gestionarComicForm.controls.editorial.enable();
        this.gestionarComicForm.controls.tematica.enable();
        this.gestionarComicForm.controls.coleccion.enable();
        this.gestionarComicForm.controls.numeroPaginas.enable();
        this.gestionarComicForm.controls.precio.enable();
        this.gestionarComicForm.controls.autores.enable();
        this.gestionarComicForm.controls.color.enable();
//        this.gestionarComicForm.controls.color.enable(); para habilitar el campo
        this.banderaEditar = true;
    }

    public editarComic(comic: any): void {
        this.router.navigate(['bienvenida', comic]);
    }

    private limpiarFormulario(): void {
        this.submitted = false;
        this.gestionarComicForm.controls.nombre.setValue(null);
        this.gestionarComicForm.controls.editorial.setValue(null);
        this.gestionarComicForm.controls.tematica.setValue(null);
        this.gestionarComicForm.controls.coleccion.setValue(null);
        this.gestionarComicForm.controls.numeroPaginas.setValue(null);
        this.gestionarComicForm.controls.precio.setValue(null);
        this.gestionarComicForm.controls.autores.setValue(null);
        this.gestionarComicForm.controls.color.setValue(null);
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() {
        return this.gestionarComicForm.controls;
    }

    /**
     * @description Metodo para eliminar Registros
     */
    public eliminarRegistro(indice: number) {
        this.eliminado = true;
        this.nombreEliminado = this.listaComics[indice].nombre;
        this.listaComics.splice(indice, 1);

    }

}
