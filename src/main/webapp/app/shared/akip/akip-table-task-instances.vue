<template>
  <div class="table-responsive m-0" v-if="taskInstances && taskInstances.length > 0">
    <table class="table table-striped" aria-describedby="taskInstances">
      <thead>
        <tr>
          <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
          <th v-for="column in columns" :key="column" scope="row">
            <span v-text="$t('airFlyerApp.taskInstance.' + column)">{{ column }}</span>
          </th>
          <th scope="row"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="taskInstance in taskInstances" :key="taskInstance.id" data-cy="entityTable">
          <td>
            <router-link
              :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/task/${taskInstance.taskDefinitionKey}/${taskInstance.id}/view`"
              >{{ taskInstance.id }}</router-link
            >
          </td>
          <td v-for="column in columns" :key="column">
            <div v-if="column == 'status'">
              <akip-show-task-instance-status :status="taskInstance.status"></akip-show-task-instance-status>
            </div>
            <div
              v-else-if="
                column == 'createDate' || column == 'createTime' || column == 'dueDate' || column == 'startTime' || column == 'endTime'
              "
            >
              {{ taskInstance[column] ? $d(Date.parse(taskInstance[column]), 'short') : '' }}
            </div>
            <div v-else>
              {{ taskInstance[column] }}
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
</template>

<script lang="ts" src="./akip-table-task-instances.component.ts"></script>

<style scoped></style>
