<template>
  <div class="card" v-if="processDefinition">
    <h5 class="card-header">
      <span class="title">
        #{{ processDefinition.id }} -
        {{ processDefinition.name }}
      </span>
    </h5>
    <div class="card-body">
      <div class="xcontainer">
        <div class="row px-3">
          <div class="col-9 description">
            <div v-if="processDefinition.description">
              <vue-markdown>{{ processDefinition.description }}</vue-markdown>
            </div>
            <slot name="body"></slot>
          </div>
          <div class="col summary">
            <div class="label" v-text="$t('airFlyerApp.processDefinition.status')">Status</div>
            <div class="link" v-if="processDefinition.status">
              <akip-show-process-definition-status :status="processDefinition.status"></akip-show-process-definition-status>
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processDefinition.camundaDeploymentId')">Camunda Deployment Id</div>
            <div class="link" v-if="processDefinition.camundaDeploymentId">
              {{ processDefinition.camundaDeploymentId }}
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processDefinition.camundaProcessDefinitionId')">Camunda Process Definition Id</div>
            <div class="link" v-if="processDefinition.camundaProcessDefinitionId">
              {{ processDefinition.camundaProcessDefinitionId }}
            </div>
            <hr />
            <div class="label" v-text="$t('airFlyerApp.processDefinition.bpmnProcessDefinitionId')">BPMN Process Definition Id</div>
            <div class="link" v-if="processDefinition.bpmnProcessDefinitionId">
              {{ processDefinition.bpmnProcessDefinitionId }}
            </div>
          </div>
        </div>
        <div class="row summary-footer">
          <span class="footer-entry" v-if="processDefinition.createTime">
            <font-awesome-icon icon="clock"></font-awesome-icon>
            Created at: {{ $d(Date.parse(processDefinition.createTime), 'long') }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./akip-show-process-definition.component.ts"></script>

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
