import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBuyPlaneTicket } from '@/shared/model/buy-plane-ticket.model';
import BuyPlaneTicketService from './buy-plane-ticket.service';

@Component
export default class BuyPlaneTicketDetailsComponent extends Vue {
  @Inject('buyPlaneTicketService') private buyPlaneTicketService: () => BuyPlaneTicketService;
  public buyPlaneTicket: IBuyPlaneTicket = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveBuyPlaneTicket(to.params.processInstanceId);
      }
    });
  }

  public retrieveBuyPlaneTicket(buyPlaneTicketId) {
    this.isFetching = true;
    this.buyPlaneTicketService()
      .find(buyPlaneTicketId)
      .then(
        res => {
          this.buyPlaneTicket = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
