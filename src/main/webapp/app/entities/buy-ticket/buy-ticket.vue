<template>
  <div>
    <h2 id="page-heading" data-cy="BuyTicketHeading">
      <span v-text="$t('airFlyerApp.buyTicket.home.title')" id="buy-ticket-heading">Buy Tickets</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.buyTicket.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && buyTickets && buyTickets.length === 0">
      <span v-text="$t('airFlyerApp.buyTicket.home.notFound')">No buyTickets found</span>
    </div>
    <div class="table-responsive" v-if="buyTickets && buyTickets.length > 0">
      <table class="table table-striped" aria-describedby="buyTickets">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.name')">Name</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.origem')">Origem</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.destino')">Destino</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.dataIda')">Data Ida</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.dataVolta')">Data Volta</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.companhiaAerea')">Companhia Aerea</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.buyTicket.cartaoCredito')">Cartao Credito</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="buyTicket in buyTickets" :key="buyTicket.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BuyTicketView', params: { buyTicketId: buyTicket.id } }">{{ buyTicket.id }}</router-link>
            </td>
            <td>{{ buyTicket.name }}</td>
            <td>{{ buyTicket.origem }}</td>
            <td>{{ buyTicket.destino }}</td>
            <td>{{ buyTicket.dataIda }}</td>
            <td>{{ buyTicket.dataVolta }}</td>
            <td>{{ buyTicket.companhiaAerea }}</td>
            <td>{{ buyTicket.cartaoCredito }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BuyTicketView', params: { buyTicketId: buyTicket.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="airFlyerApp.buyTicket.delete.question" data-cy="buyTicketDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-buyTicket-heading" v-text="$t('airFlyerApp.buyTicket.delete.question', { id: removeId })">
          Are you sure you want to delete this Buy Ticket?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-buyTicket"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeBuyTicket()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./buy-ticket.component.ts"></script>
