import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseOriginDestinationService from './task-choose-origin-destination.service';
import { TaskChooseOriginDestinationContext } from './task-choose-origin-destination.model';

const validations: any = {
  taskContext: {
    buyPlaneTicket: {
      buyTicket: {
        name: {},
        origem: {},
        destino: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseOriginDestinationExecuteComponent extends Vue {
  private taskChooseOriginDestinationService: TaskChooseOriginDestinationService = new TaskChooseOriginDestinationService();
  private taskContext: TaskChooseOriginDestinationContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseOriginDestinationService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseOriginDestinationService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
