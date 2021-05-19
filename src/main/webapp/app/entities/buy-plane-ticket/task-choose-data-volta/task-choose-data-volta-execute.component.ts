import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseDataVoltaService from './task-choose-data-volta.service';
import { TaskChooseDataVoltaContext } from './task-choose-data-volta.model';

const validations: any = {
  taskContext: {
    buyPlaneTicket: {
      buyTicket: {
        name: {},
        dataVolta: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseDataVoltaExecuteComponent extends Vue {
  private taskChooseDataVoltaService: TaskChooseDataVoltaService = new TaskChooseDataVoltaService();
  private taskContext: TaskChooseDataVoltaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseDataVoltaService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseDataVoltaService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
