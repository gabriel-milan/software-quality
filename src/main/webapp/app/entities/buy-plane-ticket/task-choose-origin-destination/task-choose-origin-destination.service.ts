import axios from 'axios';
import { TaskChooseOriginDestinationContext } from './task-choose-origin-destination.model';

const baseApiUrl = 'api/buy-plane-ticket/task-choose-origin-destination';

export default class TaskChooseOriginDestinationService {
  public loadContext(taskId: number): Promise<TaskChooseOriginDestinationContext> {
    return new Promise<TaskChooseOriginDestinationContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskChooseOriginDestinationContext> {
    return new Promise<TaskChooseOriginDestinationContext>((resolve, reject) => {
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

  public complete(taskChooseOriginDestinationContext: TaskChooseOriginDestinationContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseOriginDestinationContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
