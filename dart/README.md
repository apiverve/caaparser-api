# CAA Record Parser API - Dart/Flutter Client

CAA Record Parser analyzes DNS CAA records that specify which Certificate Authorities are authorized to issue certificates for a domain.

[![pub package](https://img.shields.io/pub/v/apiverve_caaparser.svg)](https://pub.dev/packages/apiverve_caaparser)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is the Dart/Flutter client for the [CAA Record Parser API](https://apiverve.com/marketplace/caaparser?utm_source=dart&utm_medium=readme).

## Installation

Add this to your `pubspec.yaml`:

```yaml
dependencies:
  apiverve_caaparser: ^1.1.14
```

Then run:

```bash
dart pub get
# or for Flutter
flutter pub get
```

## Usage

```dart
import 'package:apiverve_caaparser/apiverve_caaparser.dart';

void main() async {
  final client = CaaparserClient('YOUR_API_KEY');

  try {
    final response = await client.execute({
      'record': 'example.com. 3600 IN CAA 0 issue "letsencrypt.org"'
    });

    print('Status: ${response.status}');
    print('Data: ${response.data}');
  } catch (e) {
    print('Error: $e');
  }
}
```

## Response

```json
{
  "status": "ok",
  "error": null,
  "data": {
    "raw_record": "example.com. 3600 IN CAA 0 issue \"letsencrypt.org\"",
    "parsed": {
      "domain": "example.com",
      "ttl": 3600,
      "class": "IN",
      "flags": 0,
      "tag": "issue",
      "value": "letsencrypt.org"
    },
    "ca_info": {
      "name": "Let's Encrypt",
      "type": "Free",
      "wildcard_support": true
    },
    "interpretation": {
      "meaning": "Only letsencrypt.org is authorized to issue certificates",
      "restriction": "Restricted to specific CA",
      "critical": false,
      "critical_explanation": "Non-critical - CA may proceed if not understood"
    },
    "tag_description": "Authorizes a CA to issue certificates (any type)",
    "is_valid": true
  }
}
```

## API Reference

- **API Home:** [CAA Record Parser API](https://apiverve.com/marketplace/caaparser?utm_source=dart&utm_medium=readme)
- **Documentation:** [docs.apiverve.com/ref/caaparser](https://docs.apiverve.com/ref/caaparser?utm_source=dart&utm_medium=readme)

## Authentication

All requests require an API key. Get yours at [apiverve.com](https://apiverve.com?utm_source=dart&utm_medium=readme).

## License

MIT License - see [LICENSE](LICENSE) for details.

---

Built with Dart for [APIVerve](https://apiverve.com?utm_source=dart&utm_medium=readme)
