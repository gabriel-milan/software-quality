<template>
  <div>
    <h2 id="page-heading" data-cy="ProcessInstanceHeading">
      <span v-text="$t('airFlyerApp.processInstance.home.title')" id="process-instance-heading">Process Instances</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.processInstance.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && processInstances && processInstances.length === 0">
      <span v-text="$t('airFlyerApp.processInstance.home.notFound')">No processInstances found</span>
    </div>
    <div class="table-responsive" v-if="processInstances && processInstances.length > 0">
      <table class="table table-striped" aria-describedby="processInstances">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.businessKey')">Business Key</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.status')">Status</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.endDate')">End Date</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.camundaDeploymentId')">Camunda Deployment Id</span></th>
            <th scope="row">
              <span v-text="$t('airFlyerApp.processInstance.camundaProcessDefinitionId')">Camunda Process Definition Id</span>
            </th>
            <th scope="row">
              <span v-text="$t('airFlyerApp.processInstance.camundaProcessInstanceId')">Camunda Process Instance Id</span>
            </th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.camundaProcessVariables')">Camunda Process Variables</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.processInstance.processDefinition')">Process Definition</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="processInstance in processInstances" :key="processInstance.id" data-cy="entityTable">
            <td>
              <router-link
                :to="`/process-definition/${processInstance.processDefinition.bpmnProcessDefinitionId}/instance/${processInstance.id}/view`"
                >{{ processInstance.id }}</router-link
              >
            </td>
            <td>{{ processInstance.businessKey }}</td>
            <td><akip-show-process-instance-status :status="processInstance.status"></akip-show-process-instance-status></td>
            <td>{{ processInstance.startDate ? $d(Date.parse(processInstance.startDate), 'short') : '' }}</td>
            <td>{{ processInstance.endDate ? $d(Date.parse(processInstance.endDate), 'short') : '' }}</td>
            <td>{{ processInstance.camundaDeploymentId }}</td>
            <td>{{ processInstance.camundaProcessDefinitionId }}</td>
            <td>{{ processInstance.camundaProcessInstanceId }}</td>
            <td>{{ processInstance.camundaProcessVariables }}</td>
            <td>
              <div v-if="processInstance.processDefinition">
                <router-link
                  :to="{ name: 'ProcessDefinitionView', params: { processDefinitionId: processInstance.processDefinition.id } }"
                  >{{ processInstance.processDefinition.name }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/${processInstance.processDefinition.bpmnProcessDefinitionId}/instance/${processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="airFlyerApp.processInstance.delete.question"
          data-cy="processInstanceDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-processInstance-heading" v-text="$t('airFlyerApp.processInstance.delete.question', { id: removeId })">
          Are you sure you want to delete this Process Instance?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-processInstance"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProcessInstance()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./process-instance.component.ts"></script>
