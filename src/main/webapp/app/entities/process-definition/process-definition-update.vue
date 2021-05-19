<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="airFlyerApp.processDefinition.home.createOrEditLabel"
          data-cy="ProcessDefinitionCreateUpdateHeading"
          v-text="$t('airFlyerApp.processDefinition.home.createOrEditLabel')"
        >
          Create or edit a ProcessDefinition
        </h2>
        <div>
          <div class="form-group" v-if="processDefinition.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="processDefinition.id" readonly />
          </div>
          <div class="form-group" v-if="processDefinition.id">
            <label class="form-control-label" v-text="$t('airFlyerApp.processDefinition.name')" for="process-definition-name">Name</label>
            <input type="text" class="form-control" name="name" id="process-definition-name" v-model="processDefinition.name" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('airFlyerApp.processDefinition.specificationFile')"
              for="process-definition-specificationFile"
              >Specification File</label
            >
            <div>
              <div v-if="processDefinition.specificationFile" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(processDefinition.specificationFileContentType, processDefinition.specificationFile)"
                  v-text="$t('entity.action.open')"
                  >open</a
                ><br />
                <span class="pull-left"
                  >{{ processDefinition.specificationFileContentType }}, {{ byteSize(processDefinition.specificationFile) }}</span
                >
                <button
                  type="button"
                  v-on:click="
                    processDefinition.specificationFile = null;
                    processDefinition.specificationFileContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_specificationFile"
                id="file_specificationFile"
                data-cy="specificationFile"
                v-on:change="setFileData($event, processDefinition, 'specificationFile', false)"
                v-text="$t('entity.action.addblob')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="specificationFile"
              id="process-definition-specificationFile"
              data-cy="specificationFile"
              :class="{ valid: !$v.processDefinition.specificationFile.$invalid, invalid: $v.processDefinition.specificationFile.$invalid }"
              v-model="$v.processDefinition.specificationFile.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="specificationFileContentType"
              id="process-definition-specificationFileContentType"
              v-model="processDefinition.specificationFileContentType"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.processDefinition.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./process-definition-update.component.ts"></script>
