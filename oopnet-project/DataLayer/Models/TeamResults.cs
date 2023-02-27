using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class TeamResults : IComparable<TeamResults>
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

        [JsonProperty("wins")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Wins { get; set; }

        [JsonProperty("draws")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Draws { get; set; }

        [JsonProperty("losses")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Losses { get; set; }

        [JsonProperty("games_played")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int GamesPlayed { get; set; }

        [JsonProperty("points")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Points { get; set; }

        [JsonProperty("goals_for")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int GoalsFor { get; set; }

        [JsonProperty("goals_against")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int GoalsAgainst { get; set; }

        [JsonProperty("goal_differential")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int GoalDifferential { get; set; }

        public int CompareTo(TeamResults other) => FifaCode.CompareTo(other.FifaCode);

        public override string ToString() => $"{Country.ToUpper()} ({FifaCode.ToUpper()})";
    }
}
