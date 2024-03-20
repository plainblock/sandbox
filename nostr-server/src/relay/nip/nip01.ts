export type Event = {
  id: string;
  pubkey: string;
  created_at: number;
  kind: number;
  tags: string[][];
  content: string;
  sig: string;
};

export function processEvent(event: Event): string {
  return `["OK", "${event.id}", true, ""]`;
}

export function processReq(subscribeId: string, filters: string[]): string {
  return `["EOSE", "${subscribeId}"]`;
}

export function processClose(subscribeId: string): string {
  return `["CLOSED", "${subscribeId}", ""]`;
}
