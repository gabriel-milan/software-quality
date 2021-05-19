import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IProcessDefinition } from '@/shared/model/process-definition.model';
import ProcessDefinitionService from './process-definition.service';

@Component
export default class ProcessDefinitionDetails extends mixins(JhiDataUtils) {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  public processDefinition: IProcessDefinition = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processDefinitionId) {
        vm.retrieveProcessDefinition(to.params.processDefinitionId);
      }
    });
  }

  public retrieveProcessDefinition(processDefinitionId) {
    this.processDefinitionService()
      .find(processDefinitionId)
      .then(res => {
        this.processDefinition = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
