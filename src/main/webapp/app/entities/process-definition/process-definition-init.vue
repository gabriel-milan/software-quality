<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="init()">
        <h2
          id="airFlyerApp.processDefinition.home.createOrEditLabel"
          data-cy="ProcessDefinitionCreateUpdateHeading"
          v-text="$t('airFlyerApp.processDefinition.home.initLabel')"
        >
          Init the Process
        </h2>
        <akip-show-process-definition :processDefinition="processDefinition">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airFlyerApp.processInstance.businessKey')" for="businessKey"
                >Business Key</label
              >
              <input
                type="text"
                class="form-control form-control-sm"
                name="businessKey"
                id="businessKey"
                data-cy="businessKey"
                :class="{ valid: !$v.processInstance.businessKey.$invalid, invalid: $v.processInstance.businessKey.$invalid }"
                v-model="$v.processInstance.businessKey.$model"
              />
            </div>
          </template>
        </akip-show-process-definition>
        <br />
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.processInstance.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./process-definition-init.component.ts"></script>
