/**
 * Basic Example - CAA Record Parser API
 *
 * This example demonstrates how to use the CAA Record Parser API.
 * Make sure to set your API key in the .env file or replace '[YOUR_API_KEY]' below.
 */

require('dotenv').config();
const caaparserAPI = require('../index.js');

// Initialize the API client
const api = new caaparserAPI({
    api_key: process.env.API_KEY || '[YOUR_API_KEY]'
});

// Example query
var query = {
  "record": "example.com. 3600 IN CAA 0 issue \"letsencrypt.org\""
};

// Make the API request using callback
console.log('Making request to CAA Record Parser API...\n');

api.execute(query, function (error, data) {
    if (error) {
        console.error('Error occurred:');
        if (error.error) {
            console.error('Message:', error.error);
            console.error('Status:', error.status);
        } else {
            console.error(JSON.stringify(error, null, 2));
        }
        return;
    }

    console.log('Response:');
    console.log(JSON.stringify(data, null, 2));
});
