import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseOriginDestinationService from './task-choose-origin-destination.service';
import { TaskChooseOriginDestinationContext } from './task-choose-origin-destination.model';

@Component
export default class TaskChooseOriginDestinationDetailsComponent extends Vue {
  private taskChooseOriginDestinationService: TaskChooseOriginDestinationService = new TaskChooseOriginDestinationService();
  private taskContext: TaskChooseOriginDestinationContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseOriginDestinationService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
