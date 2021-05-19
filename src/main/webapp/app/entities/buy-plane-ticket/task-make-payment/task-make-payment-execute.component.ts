import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskMakePaymentService from './task-make-payment.service';
import { TaskMakePaymentContext } from './task-make-payment.model';

const validations: any = {
  taskContext: {
    buyPlaneTicket: {
      buyTicket: {
        name: {},
        cartaoCredito: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskMakePaymentExecuteComponent extends Vue {
  private taskMakePaymentService: TaskMakePaymentService = new TaskMakePaymentService();
  private taskContext: TaskMakePaymentContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskMakePaymentService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskMakePaymentService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }
}
