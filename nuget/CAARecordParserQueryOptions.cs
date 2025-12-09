using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.CAARecordParser
{
    /// <summary>
    /// Query options for the CAA Record Parser API
    /// </summary>
    public class CAARecordParserQueryOptions
    {
        /// <summary>
        /// The CAA record string to parse
        /// Example: example.com. 3600 IN CAA 0 issue "letsencrypt.org"
        /// </summary>
        [JsonProperty("record")]
        public string Record { get; set; }
    }
}
