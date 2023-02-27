using IISClient.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace IISClient.Pages
{
    public class SearchModel : PageModel
    {
        [BindProperty]
        public string Coin { get; set; } = default!;

        public IActionResult OnGet()
        {
            return Page();
        }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid || string.IsNullOrEmpty(Coin))
            {
                return Page();
            }

            var service = new SearchService.SearchServiceSoapClient(SearchService.SearchServiceSoapClient.EndpointConfiguration.SearchServiceSoap);
            try
            {
                var response = await service.SearchAsync(Coin);
                if (response != null)
                {
                    List<Coin> coins = new();
                    response.Body.SearchResult.ForEach(c => {
                        coins.Add(new Coin { Name = c.Name, Price = c.Price, PriceChange = c.PriceChange, Symbol = c.Symbol, Updated = c.Updated });
                    });
                    ViewData["coins"] = coins;
                }
            }
            catch (Exception)
            {
                ViewData["error"] = "Can't connect to server";
            }

            return Page();
        }
    }
}
