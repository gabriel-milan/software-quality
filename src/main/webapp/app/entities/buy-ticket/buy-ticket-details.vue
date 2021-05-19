<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="buyTicket">
        <h2 class="jh-entity-heading" data-cy="buyTicketDetailsHeading">
          <span v-text="$t('airFlyerApp.buyTicket.detail.title')">BuyTicket</span> {{ buyTicket.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.name')">Name</span>
          </dt>
          <dd>
            <span>{{ buyTicket.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.origem')">Origem</span>
          </dt>
          <dd>
            <span>{{ buyTicket.origem }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.destino')">Destino</span>
          </dt>
          <dd>
            <span>{{ buyTicket.destino }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.dataIda')">Data Ida</span>
          </dt>
          <dd>
            <span>{{ buyTicket.dataIda }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.dataVolta')">Data Volta</span>
          </dt>
          <dd>
            <span>{{ buyTicket.dataVolta }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.companhiaAerea')">Companhia Aerea</span>
          </dt>
          <dd>
            <span>{{ buyTicket.companhiaAerea }}</span>
          </dd>
          <dt>
            <span v-text="$t('airFlyerApp.buyTicket.cartaoCredito')">Cartao Credito</span>
          </dt>
          <dd>
            <span>{{ buyTicket.cartaoCredito }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./buy-ticket-details.component.ts"></script>
