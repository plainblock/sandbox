import express from 'express';
import expressWs from 'express-ws';

import { RelayLogger } from '../lib/logger';
import { Event, processClose, processEvent, processReq } from './nip/nip01';

export function startRelayServer(port: number): void {
  // Configure server
  const server = express();

  // Setup relay server
  const relay = expressWs(server).app;
  relay.ws('/', (ws, req) => {
    const ip = req.ip as string;
    ws.on('message', (raw) => {
      const message = raw.toString();
      RelayLogger.info(`Receive message from ${ip}: ${message}`);
      const processed = processMessage(message);
      ws.send(processed, (error) => {
        if (error) {
          RelayLogger.error(error.message);
        } else {
          RelayLogger.info(`Send message to ${ip}: ${processed}`);
        }
      });
    });
  });

  // Start relay server
  relay.listen(port);
}

function processMessage(message: string): string {
  // Parse JSON
  const data: any[] = JSON.parse(message); // eslint-disable-line

  // Handle invalid format error
  if (data.length < 1) {
    const error = new Error('invalid: message too short');
    RelayLogger.error(`${error.message}; message: ${message}`);
    return `["NOTICE", "${error.message}"]`;
  }

  // Parse message data
  switch (data[0]) {
    case 'EVENT': {
      const event = data[1] as Event;
      return processEvent(event);
    }
    case 'REQ': {
      const subscribeId = data[1] as string;
      const filters: string[] = [];
      let index = 0;
      for (const element of data) {
        if (index > 1) {
          filters.push(element as string);
        }
        index++;
      }
      return processReq(subscribeId, filters);
    }
    case 'CLOSE': {
      const subscribeId = data[1] as string;
      return processClose(subscribeId);
    }
    default: {
      const error = new Error('invalid: unknown message type');
      RelayLogger.error(`${error.message}; message: ${message}`);
      return `["NOTICE", "${error.message}"]`;
    }
  }
}
