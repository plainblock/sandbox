import express from "express";
import expressWs from "express-ws";
import log4js from "log4js";

export function startRelayServer(port: number, logLevel?: string | log4js.Level): void {
  // Configure server
  const server: express.Express = express();
  const logger: log4js.Logger = log4js.getLogger("relay");
  logLevel ? (logger.level = logLevel) : (logger.level = log4js.levels.DEBUG);
  server.use(log4js.connectLogger(logger, {}));

  // Setup relay server
  const relay: expressWs.Application = expressWs(server).app;
  relay.ws("/", (ws, req) => {
    ws.on("message", (msg) => {
      const request = JSON.parse(msg.toString());
      logger.debug(`Receive ${request}`);
      if (request[0] === "EVENT") {
        const response = `["OK", "${request[1].id}", true, ""]`;
        logger.debug(`Send ${response}`);
        ws.send(response);
      } else if (request[0] === "REQ") {
        const response = `["EOSE", "${request[1].msg}"]`;
        logger.debug(`Send ${response}`);
        ws.send(response);
      }
    });
    logger.info(req);
  });

  // Start relay server
  relay.listen(port);
}
