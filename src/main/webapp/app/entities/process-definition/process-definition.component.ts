import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProcessDefinition } from '@/shared/model/process-definition.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import ProcessDefinitionService from './process-definition.service';
import { ITaskInstance } from '@/shared/model/task-instance.model';
import entities from '@/router/entities';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProcessDefinition extends mixins(JhiDataUtils) {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  private removeId: any = null;

  public processDefinitions: IProcessDefinition[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProcessDefinitions();
  }

  public clear(): void {
    this.retrieveAllProcessDefinitions();
  }

  public retrieveAllProcessDefinitions(): void {
    this.isFetching = true;

    this.processDefinitionService()
      .retrieve()
      .then(
        res => {
          this.processDefinitions = res.data;
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

  public prepareRemove(instance: IProcessDefinition): void {
    this.removeId = instance.bpmnProcessDefinitionId;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProcessDefinition(): void {
    this.processDefinitionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('airFlyerApp.processDefinition.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProcessDefinitions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
