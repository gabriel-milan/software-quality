import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { StatusTaskInstance } from '@/shared/model/enumerations/status-task-instance.model';

@Component
export default class AkipShowTaskInstanceStatusComponent extends Vue {
  @Prop()
  status: StatusTaskInstance;
}
