import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IProcessDefinition, ProcessDefinition } from '@/shared/model/process-definition.model';
import ProcessDefinitionService from './process-definition.service';

const validations: any = {
  processDefinition: {
    specificationFile: {},
  },
};

@Component({
  validations,
})
export default class ProcessDefinitionUpdate extends mixins(JhiDataUtils) {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  public processDefinition: IProcessDefinition = new ProcessDefinition();
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

  public save(): void {
    this.isSaving = true;
    if (this.processDefinition.id) {
      this.processDefinitionService()
        .update(this.processDefinition)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('airFlyerApp.processDefinition.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.processDefinitionService()
        .create(this.processDefinition)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('airFlyerApp.processDefinition.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveProcessDefinition(processDefinitionId): void {
    this.processDefinitionService()
      .find(processDefinitionId)
      .then(res => {
        this.processDefinition = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
