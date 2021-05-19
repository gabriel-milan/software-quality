<template>
  <div>
    <h2 id="page-heading" data-cy="ProcessInstanceHeading">
      #{{ processDefinition.id }} - {{ processDefinition.name }} -
      <span>Instances</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.processInstance.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link
          :to="`/process-definition/${processDefinition.bpmnProcessDefinitionId}/init`"
          tag="button"
          class="btn btn-primary mr-2"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>
          <span class="d-none d-md-inline">Init</span>
        </router-link>
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
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="processInstance in processInstances" :key="processInstance.id" data-cy="entityTable">
            <td>
              <router-link
                :to="`/process-definition/${processInstance.processDefinition.bpmnProcessDefinitionId}/instance/${processInstance.id}/view`"
              >
                {{ processInstance.id }}
              </router-link>
            </td>
            <td>{{ processInstance.businessKey }}</td>
            <td>
              <akip-show-process-instance-status :status="processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>{{ processInstance.startDate }}</td>
            <td>{{ processInstance.endDate }}</td>
            <td>{{ processInstance.camundaDeploymentId }}</td>
            <td>{{ processInstance.camundaProcessDefinitionId }}</td>
            <td>{{ processInstance.camundaProcessInstanceId }}</td>
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
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./process-definition-instances.component.ts"></script>
