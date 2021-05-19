import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProcessDefinition, ProcessDefinition } from '@/shared/model/process-definition.model';
import ProcessDefinitionService from './process-definition.service';
import { IProcessInstance, ProcessInstance } from '@/shared/model/process-instance.model';
import ProcessInstanceService from '@/entities/process-instance/process-instance.service';
import { maxLength, minLength, required } from 'vuelidate/lib/validators';

const validations: any = {
  processInstance: {
    businessKey: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(254),
    },
  },
};

@Component({
  validations,
})
export default class ProcessDefinitionInit extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;
  public processDefinition: IProcessDefinition = new ProcessDefinition();
  public processInstance: IProcessInstance = new ProcessInstance();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processDefinitionId) {
        vm.retrieveProcessDefinition(to.params.processDefinitionId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public init(): void {
    this.isSaving = true;
    this.processInstanceService()
      .create(this.processInstance)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('airFlyerApp.processDefinition.instantiated', { param: param.id });
        return this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'info',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public retrieveProcessDefinition(processDefinitionId): void {
    this.processDefinitionService()
      .find(processDefinitionId)
      .then(res => {
        this.processDefinition = res;
        this.processInstance.processDefinition = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
