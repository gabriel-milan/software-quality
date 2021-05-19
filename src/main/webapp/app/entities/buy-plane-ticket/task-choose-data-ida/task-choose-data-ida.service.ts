import axios from 'axios';
import { TaskChooseDataIdaContext } from './task-choose-data-ida.model';

const baseApiUrl = 'api/buy-plane-ticket/task-choose-data-ida';

export default class TaskChooseDataIdaService {
  public loadContext(taskId: number): Promise<TaskChooseDataIdaContext> {
    return new Promise<TaskChooseDataIdaContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskChooseDataIdaContext> {
    return new Promise<TaskChooseDataIdaContext>((resolve, reject) => {
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

  public complete(taskChooseDataIdaContext: TaskChooseDataIdaContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseDataIdaContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
