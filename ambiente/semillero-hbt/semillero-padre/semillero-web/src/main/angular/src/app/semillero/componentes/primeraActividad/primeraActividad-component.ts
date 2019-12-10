import { Component, OnInit } from '@angular/core';

/*
*@autor Jose Eusebio Caro Muentes
*@description Componente para dar informacion de nombre y ciudad
*/

@Component({
    // tslint:disable-next-line:component-selector
    selector : 'Actividad',
    templateUrl: './primeraActividad-component.html'
})

export class ActividadComponent implements OnInit {
    /*
    *@description declaracion de varibles globales
    */

    public nombre: string;
    public ciudad: string;

    /*
    *@Description declaracion del contructor
    */
    constructor() {
    }

    ngOnInit(): void {

        // Description variable para asignar el nombre del autor

        this.nombre = 'Jose Eusebio Caro Muentes';


        // Description variable para almacenar la ciudad de residencia del autor

        this.ciudad = 'Valencia Cordoba';


    }
}
