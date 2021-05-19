import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBuyTicket } from '@/shared/model/buy-ticket.model';

import BuyTicketService from './buy-ticket.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class BuyTicket extends Vue {
  @Inject('buyTicketService') private buyTicketService: () => BuyTicketService;
  private removeId: number = null;

  public buyTickets: IBuyTicket[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBuyTickets();
  }

  public clear(): void {
    this.retrieveAllBuyTickets();
  }

  public retrieveAllBuyTickets(): void {
    this.isFetching = true;

    this.buyTicketService()
      .retrieve()
      .then(
        res => {
          this.buyTickets = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
