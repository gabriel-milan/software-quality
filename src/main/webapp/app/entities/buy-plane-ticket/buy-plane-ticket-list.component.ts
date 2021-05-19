import { Component, Vue, Inject } from 'vue-property-decorator';
import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { IBuyPlaneTicket } from '@/shared/model/buy-plane-ticket.model';

import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import BuyPlaneTicketService from './buy-plane-ticket.service';

@Component
export default class BuyPlaneTicketListComponent extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('buyPlaneTicketService') private buyPlaneTicketService: () => BuyPlaneTicketService;

  public bpmnProcessDefinitionId: string = 'BuyPlaneTicket';
  public processDefinition: IProcessDefinition = {};
  public buyPlaneTicketList: IBuyPlaneTicket[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
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
    this.buyPlaneTicketService()
      .retrieve()
      .then(
        res => {
          this.buyPlaneTicketList = res.data;
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
