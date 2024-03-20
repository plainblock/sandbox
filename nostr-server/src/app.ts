import { startRelayServer } from './relay/server';
import { startWebServer } from './web/server';

startRelayServer(18000);
startWebServer(18080);
