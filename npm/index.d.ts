declare module '@apiverve/caaparser' {
  export interface caaparserOptions {
    api_key: string;
    secure?: boolean;
  }

  /**
   * Describes fields the current plan does not unlock. Locked fields arrive as null
   * in `data`; `locked_fields` names them, using dot paths for nested fields.
   * Absent when the plan unlocks everything.
   */
  export interface PremiumInfo {
    message: string;
    upgrade_url: string;
    locked_fields: string[];
  }

  export interface caaparserResponse {
    status: string;
    error: string | null;
    data: CAARecordParserData;
    code?: number;
    premium?: PremiumInfo;
  }


  interface CAARecordParserData {
      rawRecord:      null | string;
      parsed:         Parsed;
      caInfo:         CAInfo;
      interpretation: Interpretation;
      tagDescription: null | string;
      isValid:        boolean | null;
  }
  
  interface CAInfo {
      name:            null | string;
      type:            null | string;
      wildcardSupport: boolean | null;
  }
  
  interface Interpretation {
      meaning:             null | string;
      restriction:         null | string;
      critical:            boolean | null;
      criticalExplanation: null | string;
  }
  
  interface Parsed {
      domain: null | string;
      ttl:    number | null;
      class:  null | string;
      flags:  number | null;
      tag:    null | string;
      value:  null | string;
  }

  export default class caaparserWrapper {
    constructor(options: caaparserOptions);

    execute(callback: (error: any, data: caaparserResponse | null) => void): Promise<caaparserResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: caaparserResponse | null) => void): Promise<caaparserResponse>;
    execute(query?: Record<string, any>): Promise<caaparserResponse>;
  }
}
