import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITaskInstance } from '@/shared/model/task-instance.model';

import TaskInstanceService from './task-instance.service';
import { ITask } from '@/shared/model/task.model';
import entities from '@/router/entities';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TaskInstance extends Vue {
  @Inject('taskInstanceService') private taskInstanceService: () => TaskInstanceService;

  public taskInstances: ITaskInstance[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTaskInstances();
  }

  public clear(): void {
    this.retrieveAllTaskInstances();
  }

  public retrieveAllTaskInstances(): void {
    this.isFetching = true;

    this.taskInstanceService()
      .retrieve()
      .then(
        res => {
          this.taskInstances = res.data;
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
}
