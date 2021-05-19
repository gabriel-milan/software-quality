import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseAirlineService from './task-choose-airline.service';
import { TaskChooseAirlineContext } from './task-choose-airline.model';

const validations: any = {
  taskContext: {
    buyPlaneTicket: {
      buyTicket: {
        name: {},
        companhiaAerea: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskChooseAirlineExecuteComponent extends Vue {
  private taskChooseAirlineService: TaskChooseAirlineService = new TaskChooseAirlineService();
  private taskContext: TaskChooseAirlineContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskChooseAirlineService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskChooseAirlineService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
