import axios from 'axios';

import { IBuyPlaneTicket } from '@/shared/model/buy-plane-ticket.model';

const baseApiUrl = 'api/buy-plane-tickets';

export default class BuyPlaneTicketService {
  public find(id: number): Promise<IBuyPlaneTicket> {
    return new Promise<IBuyPlaneTicket>((resolve, reject) => {
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

  public create(entity: IBuyPlaneTicket): Promise<IBuyPlaneTicket> {
    return new Promise<IBuyPlaneTicket>((resolve, reject) => {
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
}
