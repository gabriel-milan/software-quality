import axios from 'axios';

import { ITaskInstance } from '@/shared/model/task-instance.model';

const baseApiUrl = 'api/task-instances';

export default class TaskInstanceService {
  public find(id: number): Promise<ITaskInstance> {
    return new Promise<ITaskInstance>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(id: number): Promise<ITaskInstance> {
    return new Promise<ITaskInstance>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}/claim`)
        .then(res => {
          resolve(res.data);
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

  public complete(taskInstance: ITaskInstance): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskInstance)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
