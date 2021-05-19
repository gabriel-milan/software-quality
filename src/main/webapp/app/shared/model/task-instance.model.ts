import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { IProcessInstance } from '@/shared/model/process-instance.model';

import { StatusTaskInstance } from '@/shared/model/enumerations/status-task-instance.model';
export interface ITaskInstance {
  id?: number;
  taskId?: string | null;
  name?: string | null;
  status?: StatusTaskInstance | null;
  description?: string | null;
  createDate?: Date | null;
  createTime?: Date | null;
  dueDate?: Date | null;
  startTime?: Date | null;
  endTime?: Date | null;
  owner?: string | null;
  assignee?: string | null;
  executionId?: string | null;
  taskDefinitionKey?: string | null;
  suspended?: boolean | null;
  priority?: number | null;
  processDefinition?: IProcessDefinition | null;
  processInstance?: IProcessInstance | null;
}

export class TaskInstance implements ITaskInstance {
  constructor(
    public id?: number,
    public taskId?: string | null,
    public name?: string | null,
    public status?: StatusTaskInstance | null,
    public description?: string | null,
    public createDate?: Date | null,
    public createTime?: Date | null,
    public dueDate?: Date | null,
    public startTime?: Date | null,
    public endTime?: Date | null,
    public owner?: string | null,
    public assignee?: string | null,
    public executionId?: string | null,
    public taskDefinitionKey?: string | null,
    public suspended?: boolean | null,
    public priority?: number | null,
    public processDefinition?: IProcessDefinition | null,
    public processInstance?: IProcessInstance | null
  ) {
    this.suspended = this.suspended ?? false;
  }
}
