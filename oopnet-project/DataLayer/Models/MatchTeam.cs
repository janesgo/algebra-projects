using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class MatchTeam
    {
        [JsonProperty("country")]
        public string Country { get; set; }

        [JsonProperty("code")]
        public string Code { get; set; }

        [JsonProperty("goals")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Goals { get; set; }

        [JsonProperty("penalties")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Penalties { get; set; }

        public override string ToString() => $"{Country.ToUpper()} ({Code.ToUpper()})";
    }
}
