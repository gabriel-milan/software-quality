import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { StatusProcessInstance } from '@/shared/model/enumerations/status-process-instance.model';

export interface IProcessInstance {
  id?: number;
  businessKey?: string | null;
  camundaDeploymentId?: string | null;
  camundaProcessDefinitionId?: string | null;
  camundaProcessInstanceId?: string | null;
  camundaProcessVariables?: string | null;
  startDate?: Date | null;
  endDate?: Date | null;
  status?: StatusProcessInstance | null;
  processDefinition?: IProcessDefinition | null;
}

export class ProcessInstance implements IProcessInstance {
  constructor(
    public id?: number,
    public businessKey?: string | null,
    public camundaDeploymentId?: string | null,
    public camundaProcessDefinitionId?: string | null,
    public camundaProcessInstanceId?: string | null,
    public camundaProcessVariables?: string | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public status?: StatusProcessInstance | null,
    public processDefinition?: IProcessDefinition | null
  ) {}
}
