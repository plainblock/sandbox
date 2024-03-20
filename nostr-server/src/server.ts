import { startRelayServer } from "./relay";
import { startWebServer } from "./web";

startRelayServer(18000);
startWebServer(18080);
