using System.Runtime.Serialization;
using System.Text.Json.Serialization;

namespace IISApi.Models
{
    [DataContract]
    public class Coin
    {
        [DataMember(Order = 0)]
        public string Symbol { get; set; }

        [DataMember(Order = 1)]
        public string Name { get; set; }

        [DataMember(Order = 2)]
        public double Price { get; set; }

        [DataMember(Order = 3)]
        [JsonPropertyName("price_change")]
        public double PriceChange { get; set; }

        [DataMember(Order = 4)]
        public DateTime Updated { get; set; }

        public override bool Equals(object? obj)
        {
            return obj is Coin coin &&
                   Symbol == coin.Symbol &&
                   Name == coin.Name;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Symbol, Name);
        }
    }
}
