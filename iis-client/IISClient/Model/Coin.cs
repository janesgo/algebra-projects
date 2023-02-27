using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;

namespace IISClient.Model
{
    [DataContract(Namespace = "http://schemas.datacontract.org/2004/07/IISApi.Models")]
    public class Coin
    {
        [DataMember(Order = 0)]
        [Display(Name = "Symbol")]
        [Required(ErrorMessage = "Symbol is required")]
        public string Symbol { get; set; }

        [DataMember(Order = 1)]
        [Display(Name = "Name")]
        [Required(ErrorMessage = "Name is required")]
        public string Name { get; set; }

        [DataMember(Order = 2)]
        [Display(Name = "Price")]
        [Required(ErrorMessage = "Price is required")]
        [Range(0.00000000001, 999999999.999999999, ErrorMessage = "Price is not valid")]
        public double Price { get; set; }

        [DataMember(Order = 3)]
        [Display(Name = "Price change")]
        [JsonProperty("price_change")]
        [Required(ErrorMessage = "Price change is required")]
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
