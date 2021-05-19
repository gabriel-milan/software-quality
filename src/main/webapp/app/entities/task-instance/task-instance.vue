<template>
  <div>
    <h2 id="page-heading" data-cy="TaskInstanceHeading">
      <span v-text="$t('airFlyerApp.taskInstance.home.title')" id="task-instance-heading">Task Instances</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airFlyerApp.taskInstance.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && taskInstances && taskInstances.length === 0">
      <span v-text="$t('airFlyerApp.taskInstance.home.notFound')">No taskInstances found</span>
    </div>
    <div class="table-responsive" v-if="taskInstances && taskInstances.length > 0">
      <table class="table table-striped" aria-describedby="taskInstances">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.taskId')">Task Id</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.name')">Name</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.status')">Status</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.createDate')">Create Date</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.createTime')">Create Time</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.dueDate')">Due Date</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.startTime')">Start Time</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.endTime')">End Time</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.owner')">Owner</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.assignee')">Assignee</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.executionId')">Execution Id</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.taskDefinitionKey')">Task Definition Key</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.suspended')">Suspended</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.priority')">Priority</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.processDefinition')">Process Definition</span></th>
            <th scope="row"><span v-text="$t('airFlyerApp.taskInstance.processInstance')">Process Instance</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="taskInstance in taskInstances" :key="taskInstance.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TaskInstanceView', params: { taskInstanceId: taskInstance.id } }">{{
                taskInstance.id
              }}</router-link>
            </td>
            <td>{{ taskInstance.taskId }}</td>
            <td>{{ taskInstance.name }}</td>
            <td><akip-show-task-instance-status :status="taskInstance.status"></akip-show-task-instance-status></td>
            <td>{{ taskInstance.createDate ? $d(Date.parse(taskInstance.createDate), 'short') : '' }}</td>
            <td>{{ taskInstance.createTime ? $d(Date.parse(taskInstance.createTime), 'short') : '' }}</td>
            <td>{{ taskInstance.dueDate ? $d(Date.parse(taskInstance.dueDate), 'short') : '' }}</td>
            <td>{{ taskInstance.startTime ? $d(Date.parse(taskInstance.startTime), 'short') : '' }}</td>
            <td>{{ taskInstance.endTime ? $d(Date.parse(taskInstance.endTime), 'short') : '' }}</td>
            <td>{{ taskInstance.owner }}</td>
            <td>{{ taskInstance.assignee }}</td>
            <td>{{ taskInstance.executionId }}</td>
            <td>{{ taskInstance.taskDefinitionKey }}</td>
            <td>{{ taskInstance.suspended }}</td>
            <td>{{ taskInstance.priority }}</td>
            <td>
              <div v-if="taskInstance.processDefinition">
                <router-link :to="{ name: 'ProcessDefinitionView', params: { processDefinitionId: taskInstance.processDefinition.id } }">{{
                  taskInstance.processDefinition.name
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="taskInstance.processInstance && taskInstance.processDefinition">
                <router-link
                  :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/instance/${taskInstance.processInstance.id}/view`"
                  >{{ taskInstance.processInstance.businessKey }}</router-link
                >
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group" v-if="taskInstance.processDefinition">
                <router-link
                  v-if="taskInstance.status == 'NEW' || taskInstance.status == 'ASSIGNED'"
                  :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/task/${taskInstance.taskDefinitionKey}/${taskInstance.id}/execute`"
                  tag="button"
                  class="btn btn-primary btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="play"></font-awesome-icon>
                </router-link>
                <router-link
                  :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/task/${taskInstance.taskDefinitionKey}/${taskInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts" src="./task-instance.component.ts"></script>
