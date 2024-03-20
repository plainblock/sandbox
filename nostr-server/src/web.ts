import express from "express";
import log4js from "log4js";

export function startWebServer(port: number, logLevel?: string | log4js.Level): void {
  // Configure server
  const server: express.Express = express();
  const logger: log4js.Logger = log4js.getLogger("server");
  logLevel ? (logger.level = logLevel) : (logger.level = log4js.levels.DEBUG);
  server.use(log4js.connectLogger(logger, {}));

  // Configure static files
  server.use(express.static('public'));

  // Start web server
  server.listen(port);
}
