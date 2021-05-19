import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { ProcessInstance } from '@/shared/model/process-instance.model';

@Component
export default class AkipShowProcessInstanceComponent extends Vue {
  @Prop()
  processInstance: ProcessInstance;
}
