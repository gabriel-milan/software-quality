import { IProcessInstance } from '@/shared/model/process-instance.model';
import { IBuyTicket } from '@/shared/model/buy-ticket.model';

export interface IBuyPlaneTicket {
  id?: number;
  processInstance?: IProcessInstance | null;
  buyTicket?: IBuyTicket | null;
}

export class BuyPlaneTicket implements IBuyPlaneTicket {
  constructor(public id?: number, public processInstance?: IProcessInstance | null, public buyTicket?: IBuyTicket | null) {}
}
