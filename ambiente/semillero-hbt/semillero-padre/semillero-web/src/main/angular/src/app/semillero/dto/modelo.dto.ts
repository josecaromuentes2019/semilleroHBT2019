export class ObjetoComic {

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

