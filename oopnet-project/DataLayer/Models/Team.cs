using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class Team : IComparable<Team>
    {
        [JsonProperty("id")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Id { get; set; }

        [JsonProperty("country")]
        public string Country { get; set; }

        [JsonProperty("alternate_name")]
        public string AlternateName { get; set; }

        [JsonProperty("fifa_code")]
        public string FifaCode { get; set; }

        [JsonProperty("group_id")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int GroupId { get; set; }

        [JsonProperty("group_letter")]
        public string GroupLetter { get; set; }

        public int CompareTo(Team other) => FifaCode.CompareTo(other.FifaCode);

        public override string ToString() => $"{Country.ToUpper()} ({FifaCode.ToUpper()})";
    }
}
