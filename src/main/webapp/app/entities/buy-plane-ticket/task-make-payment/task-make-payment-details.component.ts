import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskMakePaymentService from './task-make-payment.service';
import { TaskMakePaymentContext } from './task-make-payment.model';

@Component
export default class TaskMakePaymentDetailsComponent extends Vue {
  private taskMakePaymentService: TaskMakePaymentService = new TaskMakePaymentService();
  private taskContext: TaskMakePaymentContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskMakePaymentService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
