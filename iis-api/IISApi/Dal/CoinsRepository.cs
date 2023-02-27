using IISApi.Models;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace IISApi.Dal
{
    public class CoinsRepository : IRepository<Coin>
    {
        private const string ENDPOINT = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
        private static readonly List<Coin> _coins = new List<Coin>();

        public void Add(Coin coin)
        {
            _coins.Add(coin);
        }

        public List<Coin> GetAll()
        {
            return GetCoins().Result;
        }

        private async static Task<List<Coin>> GetCoins()
        {
            using (var client = new HttpClient())
            {
                client.DefaultRequestHeaders.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.GetAsync(ENDPOINT);
                if (Res.IsSuccessStatusCode)
                {
                    var response = Res.Content.ReadAsStringAsync().Result;
                    var result = response != null ? JsonConvert.DeserializeObject<List<CoinApi>>(response) : new List<CoinApi>();
                    result.ForEach(c =>
                    {
                        Coin coin = new()
                        {
                            Name = c.Name ?? "",
                            Price = c.CurrentPrice ?? 0,
                            PriceChange = c.PriceChangePercentage24H ?? 0,
                            Symbol = c.Symbol ?? "",
                            Updated = c.LastUpdated ?? DateTime.MinValue
                        };

                        if (!_coins.Contains(coin))
                            _coins.Add(coin);
                    });
                    return _coins;
                }
            }
            return new List<Coin>();
        }
    }
}
