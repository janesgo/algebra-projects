using IISClient.Helper;
using IISClient.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Net.Http.Headers;
using System.Runtime.Serialization;
using System.Text;
using System.Xml;

namespace IISClient.Pages
{
    public class AddCoin : PageModel
    {
        private readonly ILogger<AddCoin> _logger;

        public AddCoin(ILogger<AddCoin> logger)
        {
            _logger = logger;
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        [BindProperty]
        public Coin Coin { get; set; } = default!;
        [BindProperty]
        public string Validate { get; set; } = default!;

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid || Coin == null)
            {
                return Page();
            }

            Coin.Updated = DateTime.Now;
            DataContractSerializer dcs = new DataContractSerializer(typeof(Coin));
            using var ms = new MemoryStream();
            using (XmlWriter writer = XmlWriter.Create(ms))
            {
                dcs.WriteObject(writer, Coin);
                writer.Flush();
            }
            string xml = Encoding.UTF8.GetString(ms.ToArray());
            string url = $"http://localhost:5071/api/coins/{Validate}";

            string token = await AuthService.Login("user", "user");

            using var client = new HttpClient();

            if (!String.IsNullOrEmpty(token))
            {
                client.DefaultRequestHeaders.Clear();
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
                try
                {
                    var respone = await client.PostAsXmlAsync(url, xml);
                    if (respone.IsSuccessStatusCode)
                    {
                        return RedirectToPage("./Index");
                    }
                }
                catch (Exception)
                {
                    ViewData["error"] = "Can't create new coin";
                }
            }
            else
            {
                ViewData["error"] = "Not authorized";
            }
            return Page();
        }
    }
}