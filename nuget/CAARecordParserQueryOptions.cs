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
        /// </summary>
        [JsonProperty("record")]
        public string Record { get; set; }
    }
}
