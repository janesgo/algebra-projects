using DataLayer.Helpers;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Models
{
    public class Match
    {
        [JsonProperty("venue")]
        public string Venue { get; set; }

        [JsonProperty("location")]
        public string Location { get; set; }

        [JsonProperty("status")]
        public Status Status { get; set; }

        [JsonProperty("time")]
        public Time Time { get; set; }

        [JsonProperty("fifa_id")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int FifaId { get; set; }

        [JsonProperty("weather")]
        public MatchWeather Weather { get; set; }

        [JsonProperty("attendance")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Attendance { get; set; }

        [JsonProperty("officials")]
        public IEnumerable<string> Officials { get; set; }

        [JsonProperty("stage_name")]
        public StageName StageName { get; set; }

        [JsonProperty("home_team_country")]
        public string HomeTeamCountry { get; set; }

        [JsonProperty("away_team_country")]
        public string AwayTeamCountry { get; set; }

        [JsonProperty("datetime")]
        public DateTime? Datetime { get; set; }

        [JsonProperty("winner")]
        public string Winner { get; set; }

        [JsonProperty("winner_code")]
        public string WinnerCode { get; set; }

        [JsonProperty("home_team")]
        public MatchTeam HomeTeam { get; set; }

        [JsonProperty("away_team")]
        public MatchTeam AwayTeam { get; set; }

        [JsonProperty("home_team_events")]
        public IEnumerable<MatchTeamEvent> HomeTeamEvents { get; set; }

        [JsonProperty("away_team_events")]
        public IEnumerable<MatchTeamEvent> AwayTeamEvents { get; set; }

        [JsonProperty("home_team_statistics")]
        public MatchTeamStatistics HomeTeamStatistics { get; set; }

        [JsonProperty("away_team_statistics")]
        public MatchTeamStatistics AwayTeamStatistics { get; set; }

        [JsonProperty("last_event_update_at")]
        public DateTime? LastEventUpdateAt { get; set; }

        [JsonProperty("last_score_update_at")]
        public DateTime? LastScoreUpdateAt { get; set; }

        public override string ToString()
        {
            {
                List<string> hmte = new List<string>();
                List<string> awte = new List<string>();

                HomeTeamEvents.ToList().ForEach(e => hmte.Add(e.ToString()));
                AwayTeamEvents.ToList().ForEach(e => awte.Add(e.ToString()));

                return $"{Venue}, {Location}, {Status}, {Time}, {FifaId}\n" +
                $"Weather: {Weather}\n" +
                $"{Attendance}, Officials: {String.Join(", ", Officials.ToArray())}\n, {StageName}, {HomeTeamCountry}, {AwayTeamCountry}, {Datetime}, {Winner}, {WinnerCode}\n" +
                $"Home team: {HomeTeam}, Away team: {AwayTeam}" +
                $"Home team events:\n{String.Join("\n", hmte.ToArray())}\n" +
                $"Away team events:\n{String.Join("\n", awte.ToArray())}\n" +
                $"{LastEventUpdateAt}, {LastScoreUpdateAt}";
            }
        }
    }
}
