<template>
  <div>
    <h2 id="page-heading" data-cy="ProcessDefinitionHeading">
      <span v-text="$t('airFlyerApp.processDefinition.home.title')" id="process-definition-heading">Process Definitions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.processDefinition.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link
          :to="{ name: 'ProcessDefinitionCreate' }"
          tag="button"
          id="jh-create-entity"
          data-cy="entityCreateButton"
          class="btn btn-primary jh-create-entity create-process-definition"
        >
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.processDefinition.home.createLabel')"> Create a new Process Definition </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && processDefinitions && processDefinitions.length === 0">
      <span v-text="$t('airFlyerApp.processDefinition.home.notFound')">No processDefinitions found</span>
    </div>
    <div class="table-responsive" v-if="processDefinitions && processDefinitions.length > 0">
      <table class="table table-striped" aria-describedby="processDefinitions">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processDefinition.name')">Name</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processDefinition.camundaDeploymentId')">Camunda Deployment Id</span></th>
            <th scope="row">
              <span v-text="$t('airFlyerApp.processDefinition.camundaProcessDefinitionId')">Camunda Process Definition Id</span>
            </th>
            <th scope="row">
              <span v-text="$t('airFlyerApp.processDefinition.bpmnProcessDefinitionId')">Bpmn Process Definition Id</span>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="processDefinition in processDefinitions" :key="processDefinition.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProcessDefinitionView', params: { processDefinitionId: processDefinition.id } }">{{
                processDefinition.id
              }}</router-link>
            </td>
            <td>{{ processDefinition.name }}</td>
            <td>{{ processDefinition.camundaDeploymentId }}</td>
            <td>{{ processDefinition.camundaProcessDefinitionId }}</td>
            <td>{{ processDefinition.bpmnProcessDefinitionId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <akip-button-process-definition-init :processDefinition="processDefinition"></akip-button-process-definition-init>

                <akip-button-process-definition-instances :processDefinition="processDefinition"></akip-button-process-definition-instances>

                <router-link
                  :to="{ name: 'ProcessDefinitionView', params: { processDefinitionId: processDefinition.bpmnProcessDefinitionId } }"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>

                <router-link
                  :to="{ name: 'ProcessDefinitionEdit', params: { processDefinitionId: processDefinition.bpmnProcessDefinitionId } }"
                  tag="button"
                  class="btn btn-primary btn-sm edit"
                  data-cy="entityEditButton"
                >
                  <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                </router-link>

                <b-button
                  v-on:click="prepareRemove(processDefinition)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="airFlyerApp.processDefinition.delete.question"
          data-cy="processDefinitionDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-processDefinition-heading" v-html="$t('airFlyerApp.processDefinition.delete.question', { id: removeId })">
          Are you sure you want to delete this Process Definition?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-processDefinition"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProcessDefinition()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./process-definition.component.ts"></script>
