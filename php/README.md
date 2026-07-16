# CAA Record Parser API - PHP Package

CAA Record Parser analyzes DNS CAA records that specify which Certificate Authorities are authorized to issue certificates for a domain.

## Installation

Install via Composer:

```bash
composer require apiverve/caaparser
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```php
<?php

require_once 'vendor/autoload.php';

use APIVerve\Caaparser\Client;

// Initialize the client
$client = new Client('YOUR_API_KEY');

// Make a request
$response = $client->execute(['record' => 'example.com. 3600 IN CAA 0 issue "letsencrypt.org"']);

// Print the response
print_r($response);
```


### Error Handling

```php
use APIVerve\Caaparser\Client;
use APIVerve\Caaparser\Exceptions\APIException;
use APIVerve\Caaparser\Exceptions\ValidationException;

try {
    $response = $client->execute(['record' => 'example.com. 3600 IN CAA 0 issue "letsencrypt.org"']);
    print_r($response['data']);
} catch (ValidationException $e) {
    echo "Validation error: " . implode(', ', $e->getErrors());
} catch (APIException $e) {
    echo "API error: " . $e->getMessage();
    echo "Status code: " . $e->getStatusCode();
}
```

### Debug Mode

```php
// Enable debug logging
$client = new Client(
    apiKey: 'YOUR_API_KEY',
    debug: true
);
```

## Example Response

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

## Requirements

- PHP 7.4 or higher
- Guzzle HTTP client

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/caaparser?utm_source=packagist&utm_medium=readme).

## Support

- Website: [https://apiverve.com/marketplace/caaparser?utm_source=php&utm_medium=readme](https://apiverve.com/marketplace/caaparser?utm_source=php&utm_medium=readme)
- Email: hello@apiverve.com

## License

This package is available under the [MIT License](LICENSE).
