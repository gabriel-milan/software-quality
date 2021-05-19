import axios from 'axios';
import { TaskMakePaymentContext } from './task-make-payment.model';

const baseApiUrl = 'api/buy-plane-ticket/task-make-payment';

export default class TaskMakePaymentService {
  public loadContext(taskId: number): Promise<TaskMakePaymentContext> {
    return new Promise<TaskMakePaymentContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskMakePaymentContext> {
    return new Promise<TaskMakePaymentContext>((resolve, reject) => {
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

  public complete(taskMakePaymentContext: TaskMakePaymentContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskMakePaymentContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
