import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseDataIdaService from './task-choose-data-ida.service';
import { TaskChooseDataIdaContext } from './task-choose-data-ida.model';

const validations: any = {
  taskContext: {
    buyPlaneTicket: {
      buyTicket: {
        name: {},
        dataIda: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseDataIdaExecuteComponent extends Vue {
  private taskChooseDataIdaService: TaskChooseDataIdaService = new TaskChooseDataIdaService();
  private taskContext: TaskChooseDataIdaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseDataIdaService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseDataIdaService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
