/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BuyTicketComponent from '@/entities/buy-ticket/buy-ticket.vue';
import BuyTicketClass from '@/entities/buy-ticket/buy-ticket.component';
import BuyTicketService from '@/entities/buy-ticket/buy-ticket.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('BuyTicket Management Component', () => {
    let wrapper: Wrapper<BuyTicketClass>;
    let comp: BuyTicketClass;
    let buyTicketServiceStub: SinonStubbedInstance<BuyTicketService>;

    beforeEach(() => {
      buyTicketServiceStub = sinon.createStubInstance<BuyTicketService>(BuyTicketService);
      buyTicketServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BuyTicketClass>(BuyTicketComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          buyTicketService: () => buyTicketServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      buyTicketServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBuyTickets();
      await comp.$nextTick();

      // THEN
      expect(buyTicketServiceStub.retrieve.called).toBeTruthy();
      expect(comp.buyTickets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
