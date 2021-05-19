import axios from 'axios';
import { TaskChooseDataVoltaContext } from './task-choose-data-volta.model';

const baseApiUrl = 'api/buy-plane-ticket/task-choose-data-volta';

export default class TaskChooseDataVoltaService {
  public loadContext(taskId: number): Promise<TaskChooseDataVoltaContext> {
    return new Promise<TaskChooseDataVoltaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskChooseDataVoltaContext> {
    return new Promise<TaskChooseDataVoltaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskChooseDataVoltaContext: TaskChooseDataVoltaContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseDataVoltaContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
