<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('airFlyerApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskInstance">
        <akip-show-task-instance :taskInstance="taskInstance"></akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="taskInstance.status == 'NEW' || taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/task/${taskInstance.taskDefinitionKey}/${taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span> Execute</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-instance-details.component.ts"></script>
