import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBuyPlaneTicket, BuyPlaneTicket } from '@/shared/model/buy-plane-ticket.model';
import { ProcessInstance } from '@/shared/model/process-instance.model';
import { BuyTicket } from '@/shared/model/buy-ticket.model';

import BuyPlaneTicketService from './buy-plane-ticket.service';
import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';

const validations: any = {
  buyPlaneTicket: {
    buyTicket: {
      name: {},
    },
  },
};

@Component({
  validations,
})
export default class BuyTicketStartFormInitComponent extends Vue {
  @Inject('buyPlaneTicketService') private buyPlaneTicketService: () => BuyPlaneTicketService;
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  public bpmnProcessDefinitionId: string = 'BuyPlaneTicket';
  public buyPlaneTicket: IBuyPlaneTicket = new BuyPlaneTicket();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initBuyTicketStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.buyPlaneTicketService()
      .create(this.buyPlaneTicket)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('airFlyerApp.buyTicketStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initBuyTicketStartForm(): void {
    this.buyPlaneTicket.buyTicket = new BuyTicket();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
      .then(res => {
        this.buyPlaneTicket.processInstance = new ProcessInstance();
        this.buyPlaneTicket.processInstance.processDefinition = res;
      });
  }
}
