import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskInstanceService from './task-instance.service';
import { TaskContext } from '@/shared/model/task-context.model';

@Component
export default class TaskInstanceExecute extends Vue {
  @Inject('taskInstanceService') private taskInstanceService: () => TaskInstanceService;

  public taskContext: TaskContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskInstanceService()
      .claim(taskInstanceId)
      .then(res => {
        this.taskContext = {};
        this.taskContext.taskInstance = res;
        this.taskContext.processDefinition = res.processDefinition;
        this.taskContext.processInstance = res.processInstance;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskInstanceService()
      .complete(this.taskContext.taskInstance)
      .then(res => {
        this.$router.go(-1);
      });
  }
}
