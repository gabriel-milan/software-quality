import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { StatusProcessDefinition } from '@/shared/model/enumerations/status-process-definition.model';

@Component
export default class AkipShowProcessDefinitionStatusComponent extends Vue {
  @Prop()
  status: StatusProcessDefinition;
}
