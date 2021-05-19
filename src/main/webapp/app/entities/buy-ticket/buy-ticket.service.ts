import axios from 'axios';

import { IBuyTicket } from '@/shared/model/buy-ticket.model';

const baseApiUrl = 'api/buy-tickets';

export default class BuyTicketService {
  public find(id: number): Promise<IBuyTicket> {
    return new Promise<IBuyTicket>((resolve, reject) => {
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
}
