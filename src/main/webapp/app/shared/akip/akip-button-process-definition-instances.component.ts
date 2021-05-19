import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { ProcessDefinition } from '@/shared/model/process-definition.model';

@Component
export default class AkipButtonProcessDefinitionInstancesComponent extends Vue {
  @Prop()
  processDefinition: ProcessDefinition;
}
