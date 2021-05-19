import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { TaskInstance } from '@/shared/model/task-instance.model';

@Component
export default class AkipShowTaskInstanceComponent extends Vue {
  @Prop()
  taskInstance: TaskInstance;
}
