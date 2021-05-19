import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseDataVoltaService from './task-choose-data-volta.service';
import { TaskChooseDataVoltaContext } from './task-choose-data-volta.model';

@Component
export default class TaskChooseDataVoltaDetailsComponent extends Vue {
  private taskChooseDataVoltaService: TaskChooseDataVoltaService = new TaskChooseDataVoltaService();
  private taskContext: TaskChooseDataVoltaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseDataVoltaService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
