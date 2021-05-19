import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITaskInstance } from '@/shared/model/task-instance.model';
import TaskInstanceService from './task-instance.service';

@Component
export default class TaskInstanceDetails extends Vue {
  @Inject('taskInstanceService') private taskInstanceService: () => TaskInstanceService;
  public taskInstance: ITaskInstance = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public retrieveTaskInstance(taskInstanceId) {
    this.taskInstanceService()
      .find(taskInstanceId)
      .then(res => {
        this.taskInstance = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
