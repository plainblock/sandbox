import express from 'express';
import log4js from 'log4js';

import { WebLogger } from '../lib/logger';

export function startWebServer(port: number): void {
  // Configure server
  const server: express.Express = express();
  server.use(log4js.connectLogger(WebLogger, {}));

  // Configure static files
  server.use(express.static('public'));

  // Start web server
  server.listen(port);
}
