using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class Group
    {
        [JsonProperty("id")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Id { get; set; }

        [JsonProperty("letter")]
        public string Letter { get; set; }

        [JsonProperty("ordered_teams")]
        public IEnumerable<TeamResults> OrderedTeams { get; set; }

        public override string ToString()
        {
            List<string> teams = new List<string>();
            OrderedTeams.ToList().ForEach(e => teams.Add(e.ToString()));

            return $"Group id: {Id}, Group letter: {Letter}\n{String.Join("\n", teams.ToArray())}";
        }
    }
}
