<template>
  <div>
    <router-link
      :to="`/process-definition/${processDefinition.bpmnProcessDefinitionId}/instances`"
      tag="button"
      class="btn btn-secondary btn-sm details"
    >
      <font-awesome-icon icon="list"></font-awesome-icon>
      <span class="d-none d-md-inline">Instances</span>
    </router-link>
  </div>
</template>

<script lang="ts" src="./akip-button-process-definition-instances.component.ts"></script>
