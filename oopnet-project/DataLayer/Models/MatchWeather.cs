using DataLayer.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class MatchWeather
    {
        [JsonProperty("humidity")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int Humidity { get; set; }

        [JsonProperty("temp_celsius")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int TempCelsius { get; set; }

        [JsonProperty("temp_farenheit")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int TempFarenheit { get; set; }

        [JsonProperty("wind_speed")]
        [JsonConverter(typeof(ParseStringConverter))]
        public int WindSpeed { get; set; }

        [JsonProperty("description")]
        public string Description { get; set; }

        public override string ToString() => $"Humidity: {Humidity}, TempCel: {TempCelsius}, TempFahr: {TempFarenheit}, Wind speed: {WindSpeed}, Description: {Description}";
    }
}
