import { Component, Vue, Inject } from 'vue-property-decorator';
import { IProcessInstance } from '@/shared/model/process-instance.model';
import { IProcessDefinition } from '@/shared/model/process-definition.model';

import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';

@Component
export default class ProcessDefinitionInstances extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  public processDefinitionId: any = 0;
  public processDefinition: IProcessDefinition = {};
  public processInstances: IProcessInstance[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processDefinitionId) {
        vm.processDefinitionId = to.params.processDefinitionId;
        vm.init();
      }
    });
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService()
      .find(this.processDefinitionId)
      .then(
        res => {
          this.processDefinition = res;
          this.isFetchingProcessDefinition = false;
        },
        err => {
          this.isFetchingProcessDefinition = false;
        }
      );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.processDefinitionService()
      .findProcessInstances(this.processDefinitionId)
      .then(
        res => {
          this.processInstances = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
