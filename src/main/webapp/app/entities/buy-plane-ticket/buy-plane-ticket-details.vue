<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="buyPlaneTicket.processInstance">
        <h2 class="jh-entity-heading" data-cy="buyPlaneTicketDetailsHeading">
          <span v-text="$t('airFlyerApp.buyPlaneTicket.detail.title')">BuyPlaneTicket</span> {{ buyPlaneTicket.id }}
        </h2>
        <akip-show-process-instance :processInstance="buyPlaneTicket.processInstance">
          <template v-slot:title>
            <span v-text="$t('airFlyerApp.buyPlaneTicket.detail.title')">BuyPlaneTicket</span> #{{ buyPlaneTicket.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="buyPlaneTicket.buyTicket">
              <h5 class="card-header">
                <span class="title"> Buy Ticket #{{ buyPlaneTicket.buyTicket.id }} </span>
              </h5>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="name"
                    id="buy-ticket-name"
                    data-cy="name"
                    v-model="buyPlaneTicket.buyTicket.name"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.origem')">origem</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="origem"
                    id="buy-ticket-origem"
                    data-cy="origem"
                    v-model="buyPlaneTicket.buyTicket.origem"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.destino')">destino</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="destino"
                    id="buy-ticket-destino"
                    data-cy="destino"
                    v-model="buyPlaneTicket.buyTicket.destino"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.dataIda')">dataIda</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="dataIda"
                    id="buy-ticket-dataIda"
                    data-cy="dataIda"
                    v-model="buyPlaneTicket.buyTicket.dataIda"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.dataVolta')">dataVolta</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="dataVolta"
                    id="buy-ticket-dataVolta"
                    data-cy="dataVolta"
                    v-model="buyPlaneTicket.buyTicket.dataVolta"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.companhiaAerea')">companhiaAerea</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="companhiaAerea"
                    id="buy-ticket-companhiaAerea"
                    data-cy="companhiaAerea"
                    v-model="buyPlaneTicket.buyTicket.companhiaAerea"
                  />
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyPlaneTicket.cartaoCredito')">cartaoCredito</label>
                  <input
                    readonly
                    type="text"
                    class="form-control form-control-sm"
                    name="cartaoCredito"
                    id="buy-ticket-cartaoCredito"
                    data-cy="cartaoCredito"
                    v-model="buyPlaneTicket.buyTicket.cartaoCredito"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./buy-plane-ticket-details.component.ts"></script>
