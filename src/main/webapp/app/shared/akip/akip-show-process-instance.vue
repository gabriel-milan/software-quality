<template>
  <div class="card" v-if="processInstance">
    <h5 class="card-header">
      <span class="title">
        <slot name="title"> Process Instance #{{ processInstance.id }} </slot>
      </span>
    </h5>
    <div class="card-body">
      <div class="xcontainer">
        <div class="row px-3">
          <div class="col-9 description">
            <slot name="details">
              <div class="form-group">
                <label class="form-control-label" v-text="$t('airFlyerApp.processInstance.businessKey')" for="process-instance-business-key"
                  >Business Key</label
                >
                <input
                  readonly
                  type="text"
                  class="form-control form-control-sm"
                  name="name"
                  id="process-instance-business-key"
                  data-cy="name"
                  v-model="processInstance.businessKey"
                />
              </div>
            </slot>
            <slot name="tasks">
              <hr />
              <div class="card">
                <h5 class="card-header">Tasks</h5>
                <div class="card-body p-0">
                  <akip-table-task-instances
                    :processInstance="processInstance"
                    :columns="['name', 'status', 'createDate', 'endTime']"
                  ></akip-table-task-instances>
                </div>
              </div>
            </slot>
          </div>
          <div class="col summary">
            <div class="label" v-text="$t('airFlyerApp.processInstance.status')">Status</div>
            <div class="link" v-if="processInstance.status">
              <akip-show-process-instance-status :status="processInstance.status"></akip-show-process-instance-status>
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processInstance.processDefinition')">Process Definition</div>
            <div class="link" v-if="processInstance.processDefinition">
              <router-link :to="`/process-definition/${processInstance.processDefinition.bpmnProcessDefinitionId}/view`">
                {{ processInstance.processDefinition.name }}
              </router-link>
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processInstance.camundaProcessInstanceId')">Camunda Process Instance Id</div>
            <div class="link" v-if="processInstance.camundaProcessInstanceId">
              {{ processInstance.camundaProcessInstanceId }}
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processInstance.camundaProcessDefinitionId')">Camunda Process Definition Id</div>
            <div class="link" v-if="processInstance.camundaProcessDefinitionId">
              {{ processInstance.camundaProcessDefinitionId }}
            </div>
            <div class="link" v-if="processInstance.processDefinition">
              <hr />
              <div class="label" v-text="$t('airFlyerApp.processDefinition.bpmnProcessDefinitionId')">BPMN Process Definition Id</div>
              <div class="link" v-if="processInstance.processDefinition.bpmnProcessDefinitionId">
                {{ processInstance.processDefinition.bpmnProcessDefinitionId }}
              </div>
            </div>
          </div>
        </div>
        <div class="row summary-footer">
          <span class="footer-entry" v-if="processInstance.createTime">
            <font-awesome-icon icon="clock"></font-awesome-icon>
            Created at: {{ $d(Date.parse(processInstance.createTime), 'long') }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./akip-show-process-instance.component.ts"></script>

<style scoped>
.title {
  float: left;
  padding-right: 0.55em;
}

.description {
  //border: 1px solid lightgray !important;
  //border-radius: 6px;
  //padding-top: 10px;
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
