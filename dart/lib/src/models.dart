/// Response models for the CAA Record Parser API.

/// API Response wrapper.
class CaaparserResponse {
  final String status;
  final dynamic error;
  final CaaparserData? data;

  /// Set only when the current plan locks one or more fields; null otherwise.
  final PremiumInfo? premium;

  CaaparserResponse({
    required this.status,
    this.error,
    this.data,
    this.premium,
  });

  factory CaaparserResponse.fromJson(Map<String, dynamic> json) => CaaparserResponse(
    status: json['status'] as String? ?? '',
    error: json['error'],
    data: json['data'] != null ? CaaparserData.fromJson(json['data']) : null,
    premium: json['premium'] != null ? PremiumInfo.fromJson(json['premium']) : null,
  );

  Map<String, dynamic> toJson() => {
    'status': status,
    if (error != null) 'error': error,
    if (data != null) 'data': data,
    if (premium != null) 'premium': premium!.toJson(),
  };
}

/// Describes fields the current plan does not unlock. Locked fields arrive as null
/// in [data], and [lockedFields] names them (nested ones use dot paths).
class PremiumInfo {
  final String? message;
  final String? upgradeUrl;
  final List<String>? lockedFields;

  PremiumInfo({this.message, this.upgradeUrl, this.lockedFields});

  factory PremiumInfo.fromJson(Map<String, dynamic> json) => PremiumInfo(
    message: json['message'] as String?,
    upgradeUrl: json['upgrade_url'] as String?,
    lockedFields: (json['locked_fields'] as List?)?.map((e) => e.toString()).toList(),
  );

  Map<String, dynamic> toJson() => {
    if (message != null) 'message': message,
    if (upgradeUrl != null) 'upgrade_url': upgradeUrl,
    if (lockedFields != null) 'locked_fields': lockedFields,
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
  num? ttl;
  String? class;
  num? flags;
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
