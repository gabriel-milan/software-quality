import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProcessInstance } from '@/shared/model/process-instance.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import ProcessInstanceService from './process-instance.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProcessInstance extends mixins(JhiDataUtils) {
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;
  private removeId: number = null;

  public processInstances: IProcessInstance[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProcessInstances();
  }

  public clear(): void {
    this.retrieveAllProcessInstances();
  }

  public retrieveAllProcessInstances(): void {
    this.isFetching = true;

    this.processInstanceService()
      .retrieve()
      .then(
        res => {
          this.processInstances = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
