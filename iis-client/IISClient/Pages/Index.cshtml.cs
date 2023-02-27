using IISClient.Model;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace IISClient.Pages
{
    public class IndexModel : PageModel
    {
        private readonly ILogger<IndexModel> _logger;
        private readonly List<Coin> _coins;

        public IndexModel(ILogger<IndexModel> logger, List<Coin> coins)
        {
            _logger = logger;
            _coins = coins;
        }

        public void OnGet()
        {
            try
            {
                GetCoins().Result.ForEach(c =>
                {
                    if (!_coins.Contains(c))
                    {
                        _coins.Add(c);
                    }
                });
                ViewData["coins"] = _coins;
            }
            catch (Exception)
            {
                ViewData["error"] = "Can't connect to API";
            }
        }

        private async static Task<List<Coin>> GetCoins()
        {
            using (var client = new HttpClient())
            {
                client.DefaultRequestHeaders.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.GetAsync("http://localhost:5071/api/coins");
                if (Res.IsSuccessStatusCode)
                {
                    var response = Res.Content.ReadAsStringAsync().Result;
                    var coins = response != null ? JsonConvert.DeserializeObject<List<Coin>>(response) : new List<Coin>();
                    return coins;
                }
            }

            return new List<Coin>();
        }
    }
}