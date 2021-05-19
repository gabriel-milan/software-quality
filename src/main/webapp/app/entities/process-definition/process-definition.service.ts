import axios from 'axios';

import { IProcessDefinition } from '@/shared/model/process-definition.model';

const baseApiUrl = 'api/process-definitions';

export default class ProcessDefinitionService {
  public find(idOrBpmnProcessDefinitionId: any): Promise<IProcessDefinition> {
    return new Promise<IProcessDefinition>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${idOrBpmnProcessDefinitionId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findProcessInstances(idOrBpmnProcessDefinitionId: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${idOrBpmnProcessDefinitionId}/instances`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IProcessDefinition): Promise<IProcessDefinition> {
    return new Promise<IProcessDefinition>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IProcessDefinition): Promise<IProcessDefinition> {
    return new Promise<IProcessDefinition>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
