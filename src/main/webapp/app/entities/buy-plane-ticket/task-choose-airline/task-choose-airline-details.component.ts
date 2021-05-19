import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskChooseAirlineService from './task-choose-airline.service';
import { TaskChooseAirlineContext } from './task-choose-airline.model';

@Component
export default class TaskChooseAirlineDetailsComponent extends Vue {
  private taskChooseAirlineService: TaskChooseAirlineService = new TaskChooseAirlineService();
  private taskContext: TaskChooseAirlineContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskChooseAirlineService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
