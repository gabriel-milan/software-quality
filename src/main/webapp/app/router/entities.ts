import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const BuyTicket = () => import('@/entities/buy-ticket/buy-ticket.vue');
// prettier-ignore
const BuyTicketDetails = () => import('@/entities/buy-ticket/buy-ticket-details.vue');
// prettier-ignore
const BuyPlaneTicketDetails = () => import('@/entities/buy-plane-ticket/buy-plane-ticket-details.vue');
// prettier-ignore
const BuyPlaneTicketList = () => import('@/entities/buy-plane-ticket/buy-plane-ticket-list.vue');
// prettier-ignore
const BuyTicketStartFormInit = () => import('@/entities/buy-plane-ticket/buy-ticket-start-form-init.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseAirlineDetails = () => import('@/entities/buy-plane-ticket/task-choose-airline/task-choose-airline-details.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseAirlineExecute = () => import('@/entities/buy-plane-ticket/task-choose-airline/task-choose-airline-execute.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseDataIdaDetails = () => import('@/entities/buy-plane-ticket/task-choose-data-ida/task-choose-data-ida-details.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseDataIdaExecute = () => import('@/entities/buy-plane-ticket/task-choose-data-ida/task-choose-data-ida-execute.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseDataVoltaDetails = () => import('@/entities/buy-plane-ticket/task-choose-data-volta/task-choose-data-volta-details.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseDataVoltaExecute = () => import('@/entities/buy-plane-ticket/task-choose-data-volta/task-choose-data-volta-execute.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseOriginDestinationDetails = () => import('@/entities/buy-plane-ticket/task-choose-origin-destination/task-choose-origin-destination-details.vue');
// prettier-ignore
const BuyPlaneTicket_TaskChooseOriginDestinationExecute = () => import('@/entities/buy-plane-ticket/task-choose-origin-destination/task-choose-origin-destination-execute.vue');
// prettier-ignore
const BuyPlaneTicket_TaskMakePaymentDetails = () => import('@/entities/buy-plane-ticket/task-make-payment/task-make-payment-details.vue');
// prettier-ignore
const BuyPlaneTicket_TaskMakePaymentExecute = () => import('@/entities/buy-plane-ticket/task-make-payment/task-make-payment-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/buy-ticket',
    name: 'BuyTicket',
    component: BuyTicket,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/buy-ticket/:buyTicketId/view',
    name: 'BuyTicketView',
    component: BuyTicketDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/instance/:processInstanceId/view',
    name: 'BuyPlaneTicketView',
    component: BuyPlaneTicketDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/instances',
    name: 'BuyPlaneTicketList',
    component: BuyPlaneTicketList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/init',
    name: 'BuyTicketStartFormInit',
    component: BuyTicketStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolheCompanhia/:taskInstanceId/view',
    name: 'BuyPlaneTicket_TaskChooseAirlineDetails',
    component: BuyPlaneTicket_TaskChooseAirlineDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolheCompanhia/:taskInstanceId/execute',
    name: 'BuyPlaneTicket_TaskChooseAirlineExecute',
    component: BuyPlaneTicket_TaskChooseAirlineExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolherDataIda/:taskInstanceId/view',
    name: 'BuyPlaneTicket_TaskChooseDataIdaDetails',
    component: BuyPlaneTicket_TaskChooseDataIdaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolherDataIda/:taskInstanceId/execute',
    name: 'BuyPlaneTicket_TaskChooseDataIdaExecute',
    component: BuyPlaneTicket_TaskChooseDataIdaExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolherDataVolta/:taskInstanceId/view',
    name: 'BuyPlaneTicket_TaskChooseDataVoltaDetails',
    component: BuyPlaneTicket_TaskChooseDataVoltaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolherDataVolta/:taskInstanceId/execute',
    name: 'BuyPlaneTicket_TaskChooseDataVoltaExecute',
    component: BuyPlaneTicket_TaskChooseDataVoltaExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolheOrigemDestino/:taskInstanceId/view',
    name: 'BuyPlaneTicket_TaskChooseOriginDestinationDetails',
    component: BuyPlaneTicket_TaskChooseOriginDestinationDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/escolheOrigemDestino/:taskInstanceId/execute',
    name: 'BuyPlaneTicket_TaskChooseOriginDestinationExecute',
    component: BuyPlaneTicket_TaskChooseOriginDestinationExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/realizaPagamento/:taskInstanceId/view',
    name: 'BuyPlaneTicket_TaskMakePaymentDetails',
    component: BuyPlaneTicket_TaskMakePaymentDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/BuyPlaneTicket/task/realizaPagamento/:taskInstanceId/execute',
    name: 'BuyPlaneTicket_TaskMakePaymentExecute',
    component: BuyPlaneTicket_TaskMakePaymentExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
