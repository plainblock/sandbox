import log4js from 'log4js';
import path from 'path';

const LOG_ROOT_DIR = process.env.LOG_ROOT_DIR || './logs';

const config: log4js.Configuration = {
  appenders: {
    console: { type: 'console' },
    relay: {
      type: 'dateFile',
      filename: path.join(LOG_ROOT_DIR, './relay.log'),
      pattern: 'yyyyMMdd',
      keepFileExt: true,
      daysToKeep: 7,
    },
    web: {
      type: 'dateFile',
      filename: path.join(LOG_ROOT_DIR, './web.log'),
      pattern: 'yyyyMMdd',
      keepFileExt: true,
      daysToKeep: 7,
    },
    access: {
      type: 'dateFile',
      filename: path.join(LOG_ROOT_DIR, './access.log'),
      pattern: 'yyyyMMdd',
      keepFileExt: true,
      daysToKeep: 7,
    },
  },
  categories: {
    default: {
      appenders: ['console'],
      level: 'ALL',
    },
    relay: {
      appenders: ['console', 'relay'],
      level: process.env.LOG_LEVEL_RELAY || 'debug',
    },
    web: {
      appenders: ['console', 'web'],
      level: process.env.LOG_LEVEL_WEB || 'debug',
    },
    access: {
      appenders: ['console', 'access'],
      level: process.env.LOG_LEVEL_ACCESS || 'info',
    },
  },
};

log4js.configure(config);

export const DefaultLogger = log4js.getLogger();
export const RelayLogger = log4js.getLogger('relay');
export const WebLogger = log4js.getLogger('web');
export const AccessLogger = log4js.getLogger('access');
