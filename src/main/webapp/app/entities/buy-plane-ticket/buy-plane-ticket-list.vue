<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="buyPlaneTicketDetailsHeading">
      <span v-text="$t('airFlyerApp.buyPlaneTicket.home.title')">BuyPlaneTicket</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.buyPlaneTicket.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/BuyPlaneTicket/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.buyPlaneTicket.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && buyPlaneTicketList && buyPlaneTicketList.length === 0">
      <span v-text="$t('airFlyerApp.buyPlaneTicket.home.notFound')">No buyPlaneTicket found</span>
    </div>
    <div class="table-responsive" v-if="buyPlaneTicketList && buyPlaneTicketList.length > 0">
      <table class="table table-striped" aria-describedby="buyPlaneTicketList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyPlaneTicket.processInstance')">Process Instance</span></th>
            <th scope="row">Buy Ticket</th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.status')">Status</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.endDate')">End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="buyPlaneTicket in buyPlaneTicketList" :key="buyPlaneTicket.id" data-cy="entityTable">
            <td>{{ buyPlaneTicket.id }}</td>
            <td>
              <div v-if="buyPlaneTicket.processInstance">
                <router-link :to="`/process-definition/BuyPlaneTicket/instance/${buyPlaneTicket.processInstance.id}/view`">
                  {{ buyPlaneTicket.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="buyPlaneTicket.buyTicket">
                <router-link :to="{ name: 'BuyTicketView', params: { buyTicketId: buyPlaneTicket.buyTicket.id } }">{{
                  buyPlaneTicket.buyTicket.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="buyPlaneTicket.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>{{ buyPlaneTicket.processInstance.startDate ? $d(Date.parse(buyPlaneTicket.processInstance.startDate), 'short') : '' }}</td>
            <td>{{ buyPlaneTicket.processInstance.endDate ? $d(Date.parse(buyPlaneTicket.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/BuyPlaneTicket/instance/${buyPlaneTicket.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./buy-plane-ticket-list.component.ts"></script>
