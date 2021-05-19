import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBuyTicket } from '@/shared/model/buy-ticket.model';
import BuyTicketService from './buy-ticket.service';

@Component
export default class BuyTicketDetails extends Vue {
  @Inject('buyTicketService') private buyTicketService: () => BuyTicketService;
  public buyTicket: IBuyTicket = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.buyTicketId) {
        vm.retrieveBuyTicket(to.params.buyTicketId);
      }
    });
  }

  public retrieveBuyTicket(buyTicketId) {
    this.buyTicketService()
      .find(buyTicketId)
      .then(res => {
        this.buyTicket = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
