<template>
  <div class="card">
    <h5 class="card-header">
      <span class="title">
        #{{ taskInstance.id }} -
        {{ taskInstance.name }}
      </span>
      <akip-show-task-instance-status :status="taskInstance.status"></akip-show-task-instance-status>
    </h5>
    <div class="card-body">
      <div class="xcontainer">
        <div class="row px-3">
          <div class="col-9 description">
            <div v-if="taskInstance.description">
              <vue-markdown>{{ taskInstance.description }}</vue-markdown>
            </div>
            <slot name="body"></slot>
          </div>
          <div class="col summary">
            <div class="label" v-text="$t('airFlyerApp.taskInstance.assignee')">Assignee</div>
            <div class="link" v-if="taskInstance.assignee">
              {{ taskInstance.assignee }}
            </div>
            <div class="link" v-else>Unassigned</div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.taskInstance.processDefinition')">Process Definition</div>
            <div class="link" v-if="taskInstance.processDefinition">
              <router-link :to="{ name: 'ProcessDefinitionView', params: { processDefinitionId: taskInstance.processDefinition.id } }">
                {{ taskInstance.processDefinition.name }}
              </router-link>
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.taskInstance.processInstance')">Process Instance</div>
            <div class="link" v-if="taskInstance.processInstance">
              <router-link
                :to="`/process-definition/${taskInstance.processDefinition.bpmnProcessDefinitionId}/instance/${taskInstance.processInstance.id}/view`"
              >
                {{ taskInstance.processInstance.businessKey }}
              </router-link>
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.taskInstance.taskDefinitionKey')">Task Definition Key</div>
            <div v-if="taskInstance.taskDefinitionKey">
              {{ taskInstance.taskDefinitionKey }}
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.taskInstance.taskId')">Task Id</div>
            <div v-if="taskInstance.taskId">
              {{ taskInstance.taskId }}
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.taskInstance.executionId')">Execution Id</div>
            <div v-if="taskInstance.executionId">
              {{ taskInstance.executionId }}
            </div>
          </div>
        </div>
        <div class="row px-3 summary-footer">
          <span class="footer-entry" v-if="taskInstance.createTime">
            <font-awesome-icon icon="clock"></font-awesome-icon>
            Created at: {{ $d(Date.parse(taskInstance.createTime), 'long') }}
          </span>
          <span class="footer-entry" v-if="taskInstance.endTime">
            <font-awesome-icon icon="clock"></font-awesome-icon>
            Completed at: {{ $d(Date.parse(taskInstance.endTime), 'long') }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./akip-show-task-instance.component.ts"></script>

<style scoped>
.title {
  float: left;
  padding-right: 0.55em;
}

.description {
  border: 1px solid lightgray !important;
  border-radius: 6px;
  padding-top: 10px;
}

.summary-footer {
  padding-top: 0.45em;
  font-size: 95%;
  color: gray;
}

.footer-entry {
  padding-right: 0.95em;
}

.summary {
  color: #586069;
}

.summary hr {
  margin-top: 0.5em;
  margin-bottom: 0.5em;
}

.label {
  font-weight: bold;
  margin-bottom: 0.25em;
}

.link a {
  font-weight: normal;
  color: #3e8acc;
}
</style>
