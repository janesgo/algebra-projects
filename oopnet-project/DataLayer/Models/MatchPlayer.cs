using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Models
{
    public class MatchPlayer
    {
        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("captain")]
        public bool Captain { get; set; }

        [JsonProperty("shirt_number")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int ShirtNumber { get; set; }

        [JsonProperty("position")]
        public Position Position { get; set; }

        public bool Favourite { get; set; }

        public Bitmap Picture { get; set; }

        public override string ToString() => Name;
    }
}
