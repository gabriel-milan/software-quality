import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IProcessInstance } from '@/shared/model/process-instance.model';
import ProcessInstanceService from './process-instance.service';

@Component
export default class ProcessInstanceDetails extends mixins(JhiDataUtils) {
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;

  public processInstance: IProcessInstance = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveProcessInstance(to.params.processInstanceId);
      }
    });
  }

  public retrieveProcessInstance(id) {
    this.isFetching = true;
    this.processInstanceService()
      .find(id)
      .then(
        res => {
          this.processInstance = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
