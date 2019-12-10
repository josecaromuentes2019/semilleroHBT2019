import { Component, OnInit } from '@angular/core';
import { ObjetoComic } from '../../dto/modelo.dto';

/*
*@autor Jose Eusebio Caro Muentes
*@description Componente para dar informacion de nombre y ciudad
*/

@Component({
    // tslint:disable-next-line:component-selector
    selector : 'Actividad',
    templateUrl: './GestionarObjeto.component.html'
})

export class GestionarObjetoComponent implements OnInit {
    /*
    *@description declaracion de varibles globales
    */

    public nombre: string;
    public ciudad: string;
    private arrayObjetos: Array<ObjetoComic>;
    /*
    *arreglo para pasar lo objetos a string
    */
    public arrayVista: Array<string>;
    /*
    *@ description variable para recuperar el onjeto eliminado
    */
    public valorEliminado: string;
    /*
    *@description variable para mostrar el div que contiene el
    *objeto eliminado
    */
    public mostrarMensaje: boolean;
    /*
    *@description variable para pasar el numero del objeto a eliminar
    *esta variable esta asociada por ngModel
    */
    public numero: number;

    /*
    *@Description declaracion del contructor
    */
    constructor() {
    }

    /*
    *@description metodo que se ejecuta despues del construnctor
    */
    ngOnInit(): void {

        // @description variable para asignar el nombre del autor
        this.nombre = 'Jose Eusebio Caro Muentes';

        // @description variable para almacenar la ciudad de residencia del autor
        this.ciudad = 'Valencia Cordoba';

        //@description variable para mostra u ocultar mensaje (ture/false)
        this.mostrarMensaje = false;

        // @description Arreglo para almacenar 5 instancias de ObjetoComic
        this.arrayObjetos = [
            new ObjetoComic(1, 'Batman', 'Marvel', 'Accion', 50, 'Pedro Barrera', true, new Date('2019-12-10'), 'Activo'),
            new ObjetoComic(2, 'Robin', 'Mauren', 'Ficcion', 50, 'Juan Madera', false, new Date('2019-12-11'), 'Activo'),
            new ObjetoComic(3, 'Juancho', 'Laura', 'Ficcion', 100, 'Carlos Lopez', true, new Date('2019-12-13'), 'Activo'),
            new ObjetoComic(4, 'Luis', 'Esther', 'Accion', 100, 'Maria Perez', true, new Date('2019-12-14'), 'Activo'),
            new ObjetoComic(5, 'Roberth', 'Jose', 'Accion', 120, 'Carlos Diaz', true, new Date('2019-12-15'), 'Activo')

        ];

        //se llama al metodo ObjetosJSONstring() para llenar arrayVista con string
        this.arrayVista = this.ObjetosJSONstring();
        console.log( this.arrayVista);


    }

    /*
    *@description metodo para combertir los objetos a string, es declarado private porque
    *no es necesario llamarlo desde fuera de clase
    */
    private ObjetosJSONstring (): Array<string> {
        let lista: string [] = [];
        this.arrayObjetos.forEach(valor => {
            lista.push(JSON.stringify(valor) );
        });

        return lista;
    }

    /*
    *@description metodo para eliminar elemeto del arrays de objtos
    *este metodo tambien elimina de arryVista para que se actualice la vista por medio
    *ngModel
    */

    public eliminarElemeto() {
        this.valorEliminado = JSON.stringify(this.arrayObjetos[this.numero].nombre);
        this.arrayObjetos.splice(this.numero, 1);
        this.arrayVista.splice(this.numero, 1);
        this.mostrarMensaje = true;

    }

}
