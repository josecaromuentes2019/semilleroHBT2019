/**
 *clase para hacer un modelo de datos
 */
export class ObjetoComic {
//construnctor con inyeccion (setea los valor en el contructor)
    constructor(
                public id: number,
                public nombre: string,
                public editorial: string,
                public tematica: string,
                public numeroPaginas: number,
                public autores: string,
                public aColor: boolean,
                public fechaVenta: Date,
                public estado: string) {}
}

