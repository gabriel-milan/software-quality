<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="airFlyerApp.buyTicketStartForm.home.createOrEditLabel"
          data-cy="BuyTicketStartFormCreateUpdateHeading"
          v-text="$t('airFlyerApp.buyTicketStartForm.home.createOrEditLabel')"
        >
          Create or edit a BuyTicketStartForm
        </h2>
        <div v-if="buyPlaneTicket.processInstance">
          <akip-show-process-definition :processDefinition="buyPlaneTicket.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="buyPlaneTicket.buyTicket">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airFlyerApp.buyTicketStartForm.name')" for="buy-ticket-start-form-name"
                    >Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="buy-ticket-start-form-name"
                    data-cy="name"
                    :class="{ valid: !$v.buyPlaneTicket.buyTicket.name.$invalid, invalid: $v.buyPlaneTicket.buyTicket.name.$invalid }"
                    v-model="$v.buyPlaneTicket.buyTicket.name.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.buyPlaneTicket.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./buy-ticket-start-form-init.component.ts"></script>
