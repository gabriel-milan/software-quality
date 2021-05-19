<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="processInstance">
        <h2 class="jh-entity-heading" data-cy="processInstanceDetailsHeading">
          <span v-text="$t('airFlyerApp.processInstance.detail.title')">ProcessInstance</span>
        </h2>
        <akip-show-process-instance :processInstance="processInstance"></akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./process-instance-details.component.ts"></script>
