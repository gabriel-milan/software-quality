/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import BuyTicketDetailComponent from '@/entities/buy-ticket/buy-ticket-details.vue';
import BuyTicketClass from '@/entities/buy-ticket/buy-ticket-details.component';
import BuyTicketService from '@/entities/buy-ticket/buy-ticket.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('BuyTicket Management Detail Component', () => {
    let wrapper: Wrapper<BuyTicketClass>;
    let comp: BuyTicketClass;
    let buyTicketServiceStub: SinonStubbedInstance<BuyTicketService>;

    beforeEach(() => {
      buyTicketServiceStub = sinon.createStubInstance<BuyTicketService>(BuyTicketService);

      wrapper = shallowMount<BuyTicketClass>(BuyTicketDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { buyTicketService: () => buyTicketServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBuyTicket = { id: 123 };
        buyTicketServiceStub.find.resolves(foundBuyTicket);

        // WHEN
        comp.retrieveBuyTicket(123);
        await comp.$nextTick();

        // THEN
        expect(comp.buyTicket).toBe(foundBuyTicket);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBuyTicket = { id: 123 };
        buyTicketServiceStub.find.resolves(foundBuyTicket);

        // WHEN
        comp.beforeRouteEnter({ params: { buyTicketId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.buyTicket).toBe(foundBuyTicket);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
