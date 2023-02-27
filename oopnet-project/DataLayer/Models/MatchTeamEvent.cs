using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Models
{
    public class MatchTeamEvent
    {
        [JsonProperty("id")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Id { get; set; }

        [JsonProperty("type_of_event")]
        public TypeOfEvent TypeOfEvent { get; set; }

        [JsonProperty("player")]
        public string Player { get; set; }

        [JsonProperty("time")]
        public string Time { get; set; }

        public override string ToString() => $"Id: {Id}, Event Type: {TypeOfEvent}, Player: {Player}, Time: {Time}";
    }
}
