import express from 'express';
import expressWs from 'express-ws';

import { RelayLogger } from '../lib/logger';
import { Event, processClose, processEvent, processReq } from './nip/nip01';

export function startRelayServer(port: number): void {
  // Configure server
  const server: express.Express = express();

  // Setup relay server
  const relay: expressWs.Application = expressWs(server).app;
  relay.ws('/', (ws, req) => {
    ws.on('message', (raw) => {
      const message: string = raw.toString();
      RelayLogger.info(`Receive message from ${req.ip}: ${message}`);
      const processed: string = processMessage(message);
      ws.send(processed, (error) => {
        if (error) {
          RelayLogger.error(error.message);
        } else {
          RelayLogger.info(`Send message to ${req.ip}: ${processed}`);
        }
      });
    });
  });

  // Start relay server
  relay.listen(port);
}

function processMessage(message: string): string {
  // Parse JSON
  const data: any = JSON.parse(message);

  // Handle invalid format error
  let error: string;
  if (data.length < 1) {
    error = 'invalid: message too short';
    RelayLogger.error(`${error}; message: ${message}`);
    return `["NOTICE", "${error}"]`;
  }

  // Parse message data
  let subscribeId: string;
  switch (data[0]) {
    case 'EVENT':
      const event: Event = data[1] as Event;
      return processEvent(event);
    case 'REQ':
      subscribeId = data[1] as string;
      const filters: string[] = [];
      let index: number = 0;
      for (const element of data) {
        if (index > 1) {
          filters.push(element);
        }
        index++;
      }
      return processReq(subscribeId, filters);
    case 'CLOSE':
      subscribeId = data[1] as string;
      return processClose(subscribeId);
    default:
      error = 'invalid: unknown message type';
      RelayLogger.error(`${error}; message: ${message}`);
      return `["NOTICE", "${error}"]`;
  }
}
