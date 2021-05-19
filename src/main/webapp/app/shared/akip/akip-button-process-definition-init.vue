<template>
  <div>
    <router-link
      :to="`/process-definition/${processDefinition.bpmnProcessDefinitionId}/init`"
      tag="button"
      class="btn btn-primary btn-sm details"
    >
      <font-awesome-icon icon="play"></font-awesome-icon>
      <span class="d-none d-md-inline">Init</span>
    </router-link>
  </div>
</template>

<script lang="ts" src="./akip-button-process-definition-init.component.ts"></script>
