import express from "express";
import fs from "fs";
import log4js from "log4js";

export function startWebServer(port: number, logLevel?: string | log4js.Level): void {
  // Configure server
  const server: express.Express = express();
  const logger: log4js.Logger = log4js.getLogger("server");
  logLevel ? (logger.level = logLevel) : (logger.level = log4js.levels.DEBUG);
  server.use(log4js.connectLogger(logger, {}));

  // Setup Web server
  server.get("/", (req, res) => {
    fs.readFile("./public/index.html", (err, data) => {
      res.writeHead(200, { "Content-Type": "text/html" });
      res.write(data);
      res.end();
    });
  });

  server.listen(port);
}
