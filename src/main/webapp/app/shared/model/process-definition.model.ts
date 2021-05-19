import { StatusProcessDefinition } from '@/shared/model/enumerations/status-process-definition.model';
export interface IProcessDefinition {
  id?: number;
  name?: string | null;
  description?: string | null;
  status?: StatusProcessDefinition | null;
  specificationFileContentType?: string | null;
  specificationFile?: string | null;
  camundaDeploymentMessage?: string | null;
  camundaDeploymentId?: string | null;
  camundaProcessDefinitionId?: string | null;
  bpmnProcessDefinitionId?: string | null;
}

export class ProcessDefinition implements IProcessDefinition {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public status?: StatusProcessDefinition | null,
    public specificationFileContentType?: string | null,
    public specificationFile?: string | null,
    public camundaDeploymentMessage?: string | null,
    public camundaDeploymentId?: string | null,
    public camundaProcessDefinitionId?: string | null,
    public bpmnProcessDefinitionId?: string | null
  ) {}
}
