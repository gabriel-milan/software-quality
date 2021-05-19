import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { StatusTaskInstance } from '@/shared/model/enumerations/status-task-instance.model';
import { StatusProcessInstance } from '@/shared/model/enumerations/status-process-instance.model';

@Component
export default class AkipShowProcessInstanceStatusComponent extends Vue {
  @Prop()
  status: StatusProcessInstance;
}
