<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('airFlyerApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airFlyerApp.taskChooseDataVolta.name')">name</label>
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="name"
                id="buy-ticket-name"
                data-cy="name"
                v-model="taskContext.buyPlaneTicket.buyTicket.name"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airFlyerApp.taskChooseDataVolta.dataVolta')">dataVolta</label>
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="dataVolta"
                id="buy-ticket-dataVolta"
                data-cy="dataVolta"
                v-model="taskContext.buyPlaneTicket.buyTicket.dataVolta"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/BuyPlaneTicket/task/escolherDataVolta/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-choose-data-volta-details.component.ts"></script>
