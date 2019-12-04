import { Component, OnInit } from '@angular/core';

/*
*@autor Jose Eusebio Caro Muentes
*@description Componente para dar informacion de nombre y ciudad
*/

@Component({
    selector:'Actividad',
    templateUrl: './primeraActividad-component.html'
})

export class ActividadComponent implements OnInit {
    /*
    *@description declaracion de vaaribles globales
    */

    public nombre: string;
    public ciudad: string;

    /*
    *@Description declaracion del contructor
    */
    constructor() {
    }

    ngOnInit(): void {

        /*
        *@Description variable para asignar el nombre del auto
        */
        this.nombre = 'Jose Eusebio Caro Muentes';

        /*
        * @Description variable para almacenar la ciudad de residencia del autor
        */
        this.ciudad = 'Valencia Cordoba';


    }
}