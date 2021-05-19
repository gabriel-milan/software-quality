export interface IBuyTicket {
  id?: number;
  name?: string | null;
  origem?: string | null;
  destino?: string | null;
  dataIda?: Date | null;
  dataVolta?: Date | null;
  companhiaAerea?: string | null;
  cartaoCredito?: string | null;
}

export class BuyTicket implements IBuyTicket {
  constructor(
    public id?: number,
    public name?: string | null,
    public origem?: string | null,
    public destino?: string | null,
    public dataIda?: Date | null,
    public dataVolta?: Date | null,
    public companhiaAerea?: string | null,
    public cartaoCredito?: string | null
  ) {}
}
