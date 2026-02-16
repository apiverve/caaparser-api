/// Response models for the CAA Record Parser API.

/// API Response wrapper.
class CaaparserResponse {
  final String status;
  final dynamic error;
  final CaaparserData? data;

  CaaparserResponse({
    required this.status,
    this.error,
    this.data,
  });

  factory CaaparserResponse.fromJson(Map<String, dynamic> json) => CaaparserResponse(
    status: json['status'] as String? ?? '',
    error: json['error'],
    data: json['data'] != null ? CaaparserData.fromJson(json['data']) : null,
  );

  Map<String, dynamic> toJson() => {
    'status': status,
    if (error != null) 'error': error,
    if (data != null) 'data': data,
  };
}

/// Response data for the CAA Record Parser API.

class CaaparserData {
  String? rawRecord;
  CaaparserDataParsed? parsed;
  CaaparserDataCaInfo? caInfo;
  CaaparserDataInterpretation? interpretation;
  String? tagDescription;
  bool? isValid;

  CaaparserData({
    this.rawRecord,
    this.parsed,
    this.caInfo,
    this.interpretation,
    this.tagDescription,
    this.isValid,
  });

  factory CaaparserData.fromJson(Map<String, dynamic> json) => CaaparserData(
      rawRecord: json['raw_record'],
      parsed: json['parsed'] != null ? CaaparserDataParsed.fromJson(json['parsed']) : null,
      caInfo: json['ca_info'] != null ? CaaparserDataCaInfo.fromJson(json['ca_info']) : null,
      interpretation: json['interpretation'] != null ? CaaparserDataInterpretation.fromJson(json['interpretation']) : null,
      tagDescription: json['tag_description'],
      isValid: json['is_valid'],
    );
}

class CaaparserDataParsed {
  String? domain;
  int? ttl;
  String? class;
  int? flags;
  String? tag;
  String? value;

  CaaparserDataParsed({
    this.domain,
    this.ttl,
    this.class,
    this.flags,
    this.tag,
    this.value,
  });

  factory CaaparserDataParsed.fromJson(Map<String, dynamic> json) => CaaparserDataParsed(
      domain: json['domain'],
      ttl: json['ttl'],
      class: json['class'],
      flags: json['flags'],
      tag: json['tag'],
      value: json['value'],
    );
}

class CaaparserDataCaInfo {
  String? name;
  String? type;
  bool? wildcardSupport;

  CaaparserDataCaInfo({
    this.name,
    this.type,
    this.wildcardSupport,
  });

  factory CaaparserDataCaInfo.fromJson(Map<String, dynamic> json) => CaaparserDataCaInfo(
      name: json['name'],
      type: json['type'],
      wildcardSupport: json['wildcard_support'],
    );
}

class CaaparserDataInterpretation {
  String? meaning;
  String? restriction;
  bool? critical;
  String? criticalExplanation;

  CaaparserDataInterpretation({
    this.meaning,
    this.restriction,
    this.critical,
    this.criticalExplanation,
  });

  factory CaaparserDataInterpretation.fromJson(Map<String, dynamic> json) => CaaparserDataInterpretation(
      meaning: json['meaning'],
      restriction: json['restriction'],
      critical: json['critical'],
      criticalExplanation: json['critical_explanation'],
    );
}

class CaaparserRequest {
  String record;

  CaaparserRequest({
    required this.record,
  });

  Map<String, dynamic> toJson() => {
      'record': record,
    };
}
