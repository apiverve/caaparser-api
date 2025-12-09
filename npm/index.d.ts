declare module '@apiverve/caaparser' {
  export interface caaparserOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface caaparserResponse {
    status: string;
    error: string | null;
    data: CAARecordParserData;
    code?: number;
  }


  interface CAARecordParserData {
      rawRecord:      string;
      parsed:         Parsed;
      caInfo:         CAInfo;
      interpretation: Interpretation;
      tagDescription: string;
      isValid:        boolean;
  }
  
  interface CAInfo {
      name:            string;
      type:            string;
      wildcardSupport: boolean;
  }
  
  interface Interpretation {
      meaning:             string;
      restriction:         string;
      critical:            boolean;
      criticalExplanation: string;
  }
  
  interface Parsed {
      domain: string;
      ttl:    number;
      class:  string;
      flags:  number;
      tag:    string;
      value:  string;
  }

  export default class caaparserWrapper {
    constructor(options: caaparserOptions);

    execute(callback: (error: any, data: caaparserResponse | null) => void): Promise<caaparserResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: caaparserResponse | null) => void): Promise<caaparserResponse>;
    execute(query?: Record<string, any>): Promise<caaparserResponse>;
  }
}
