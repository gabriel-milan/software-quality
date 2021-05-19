<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('airFlyerApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airFlyerApp.taskChooseDataVolta.name')" for="buy-ticket-name">name</label>
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
              <label class="form-control-label" v-text="$t('airFlyerApp.taskChooseDataVolta.dataVolta')" for="buy-ticket-dataVolta"
                >dataVolta</label
              >
              <input
                type="text"
                class="form-control"
                name="dataVolta"
                id="task-choose-data-volta-dataVolta"
                data-cy="dataVolta"
                :class="{
                  valid: !$v.taskContext.buyPlaneTicket.buyTicket.dataVolta.$invalid,
                  invalid: $v.taskContext.buyPlaneTicket.buyTicket.dataVolta.$invalid,
                }"
                v-model="$v.taskContext.buyPlaneTicket.buyTicket.dataVolta.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-choose-data-volta-execute.component.ts"></script>
