using Newtonsoft.Json;

namespace IISApi.Models
{
    public class CoinApi
    {
        [JsonProperty("id")]
        public string? Id { get; set; }

        [JsonProperty("symbol")]
        public string? Symbol { get; set; }

        [JsonProperty("name")]
        public string? Name { get; set; }

        [JsonProperty("image")]
        public string? Image { get; set; }

        [JsonProperty("current_price")]
        public double? CurrentPrice { get; set; }

        [JsonProperty("market_cap")]
        public long? MarketCap { get; set; }

        [JsonProperty("market_cap_rank")]
        public long? MarketCapRank { get; set; }

        [JsonProperty("fully_diluted_valuation")]
        public long? FullyDilutedValuation { get; set; }

        [JsonProperty("total_volume")]
        public long? TotalVolume { get; set; }

        [JsonProperty("high_24h")]
        public long? High24H { get; set; }

        [JsonProperty("low_24h")]
        public long? Low24H { get; set; }

        [JsonProperty("price_change_24h")]
        public double? PriceChange24H { get; set; }

        [JsonProperty("price_change_percentage_24h")]
        public double? PriceChangePercentage24H { get; set; }

        [JsonProperty("market_cap_change_24h")]
        public double? MarketCapChange24H { get; set; }

        [JsonProperty("market_cap_change_percentage_24h")]
        public double? MarketCapChangePercentage24H { get; set; }

        [JsonProperty("circulating_supply")]
        public long? CirculatingSupply { get; set; }

        [JsonProperty("total_supply")]
        public long? TotalSupply { get; set; }

        [JsonProperty("max_supply")]
        public long? MaxSupply { get; set; }

        [JsonProperty("ath")]
        public long? Ath { get; set; }

        [JsonProperty("ath_change_percentage")]
        public double? AthChangePercentage { get; set; }

        [JsonProperty("ath_date")]
        public DateTime? AthDate { get; set; }

        [JsonProperty("atl")]
        public double? Atl { get; set; }

        [JsonProperty("atl_change_percentage")]
        public double? AtlChangePercentage { get; set; }

        [JsonProperty("atl_date")]
        public DateTime? AtlDate { get; set; }

        [JsonProperty("roi")]
        public object? Roi { get; set; }

        [JsonProperty("last_updated")]
        public DateTime? LastUpdated { get; set; }
    }
}
