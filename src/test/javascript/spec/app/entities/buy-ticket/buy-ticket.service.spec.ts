/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import BuyTicketService from '@/entities/buy-ticket/buy-ticket.service';
import { BuyTicket } from '@/shared/model/buy-ticket.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('BuyTicket Service', () => {
    let service: BuyTicketService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BuyTicketService();
      currentDate = new Date();
      elemDefault = new BuyTicket(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dataIda: dayjs(currentDate).format(DATE_FORMAT),
            dataVolta: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of BuyTicket', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            origem: 'BBBBBB',
            destino: 'BBBBBB',
            dataIda: dayjs(currentDate).format(DATE_FORMAT),
            dataVolta: dayjs(currentDate).format(DATE_FORMAT),
            companhiaAerea: 'BBBBBB',
            cartaoCredito: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dataIda: currentDate,
            dataVolta: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of BuyTicket', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
