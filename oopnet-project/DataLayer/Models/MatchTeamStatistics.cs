using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class MatchTeamStatistics
    {
        [JsonProperty("country")]
        public string Country { get; set; }

        [JsonProperty("attempts_on_goal")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int AttemptsOnGoal { get; set; }

        [JsonProperty("on_target")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int OnTarget { get; set; }

        [JsonProperty("off_target")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int OffTarget { get; set; }

        [JsonProperty("blocked")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Blocked { get; set; }

        [JsonProperty("woodwork")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Woodwork { get; set; }

        [JsonProperty("corners")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Corners { get; set; }

        [JsonProperty("offsides")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Offsides { get; set; }

        [JsonProperty("ball_possession")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int BallPossession { get; set; }

        [JsonProperty("pass_accuracy")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int PassAccuracy { get; set; }

        [JsonProperty("num_passes")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int NumPasses { get; set; }

        [JsonProperty("passes_completed")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int PassesCompleted { get; set; }

        [JsonProperty("distance_covered")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int DistanceCovered { get; set; }

        [JsonProperty("balls_recovered")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int BallsRecovered { get; set; }

        [JsonProperty("tackles")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Tackles { get; set; }

        [JsonProperty("clearances")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Clearances { get; set; }

        [JsonProperty("yellow_cards")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int YellowCards { get; set; }

        [JsonProperty("red_cards")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int RedCards { get; set; }

        [JsonProperty("fouls_committed")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int FoulsCommitted { get; set; }

        [JsonProperty("tactics")]
        public string Tactics { get; set; }

        [JsonProperty("starting_eleven")]
        public IEnumerable<MatchPlayer> StartingEleven { get; set; }

        [JsonProperty("substitutes")]
        public IEnumerable<MatchPlayer> Substitutes { get; set; }

        public override string ToString()
        {
            List<string> se = new List<string>();
            List<string> sub = new List<string>();

            StartingEleven.ToList().ForEach(e => se.Add(e.ToString()));
            Substitutes.ToList().ForEach(e => sub.Add(e.ToString()));

            return $"{Country}, {AttemptsOnGoal}, {OnTarget}, {OffTarget}, {Blocked}, {Woodwork}, {Corners}, {Offsides}, {BallPossession}, {PassAccuracy}, {NumPasses}, {PassesCompleted}, {DistanceCovered}, {BallsRecovered}, {Tackles}, {Clearances}, {YellowCards}, {RedCards}, {FoulsCommitted}, {Tactics}\n" +
                $"Starting eleven: {String.Join("\n", se.ToArray())}\n" +
                $"Subtitutes: {String.Join("\n", sub.ToArray())}\n";
        }
    }
}
