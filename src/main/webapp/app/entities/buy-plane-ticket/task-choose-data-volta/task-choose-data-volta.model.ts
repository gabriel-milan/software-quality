import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IBuyPlaneTicket } from '@/shared/model/buy-plane-ticket.model';

export class TaskChooseDataVoltaContext {
  taskInstance?: ITaskInstance = {};
  buyPlaneTicket?: IBuyPlaneTicket = {};
}
