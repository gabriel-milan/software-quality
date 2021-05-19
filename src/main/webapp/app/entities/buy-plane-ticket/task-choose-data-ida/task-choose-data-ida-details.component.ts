import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseDataIdaService from './task-choose-data-ida.service';
import { TaskChooseDataIdaContext } from './task-choose-data-ida.model';

@Component
export default class TaskChooseDataIdaDetailsComponent extends Vue {
  private taskChooseDataIdaService: TaskChooseDataIdaService = new TaskChooseDataIdaService();
  private taskContext: TaskChooseDataIdaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseDataIdaService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
