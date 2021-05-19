import axios from 'axios';
import { TaskChooseAirlineContext } from './task-choose-airline.model';

const baseApiUrl = 'api/buy-plane-ticket/task-choose-airline';

export default class TaskChooseAirlineService {
  public loadContext(taskId: number): Promise<TaskChooseAirlineContext> {
    return new Promise<TaskChooseAirlineContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskChooseAirlineContext> {
    return new Promise<TaskChooseAirlineContext>((resolve, reject) => {
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

  public complete(taskChooseAirlineContext: TaskChooseAirlineContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskChooseAirlineContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
