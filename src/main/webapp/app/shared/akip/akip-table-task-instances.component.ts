import Component from 'vue-class-component';
import { Inject, Prop, Vue, Watch } from 'vue-property-decorator';
import { ITaskInstance } from '@/shared/model/task-instance.model';
import { ProcessInstance } from '@/shared/model/process-instance.model';
import ProcessInstanceService from '@/entities/process-instance/process-instance.service';

@Component
export default class AkipTableTaskInstancesComponent extends Vue {
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;

  public isFetching: boolean = false;
  public taskInstances: ITaskInstance[] = [];

  @Prop()
  processInstance: ProcessInstance;

  @Prop()
  columns: string[];

  @Watch('processInstance', { immediate: true, deep: true })
  public retrieveProcessInstanceTasks(): void {
    if (!this.processInstance.id) {
      return;
    }
    this.isFetching = true;
    this.processInstanceService()
      .findTaskInstances(this.processInstance.id)
      .then(
        res => {
          this.taskInstances = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }
}
