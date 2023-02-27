using CookComputing.XmlRpc;
using IISClient.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace IISClient.Pages
{
    public class DhmzModel : PageModel
    {
        public const int NO_DATA = 9999;

        [BindProperty]
        public string City { get; set; } = default!;

        public IActionResult OnGet()
        {
            return Page();
        }

        public IActionResult OnPost()
        {
            if (!ModelState.IsValid || string.IsNullOrEmpty(City))
            {
                return Page();
            }

            ITemperature server = XmlRpcProxyGen.Create<ITemperature>();
            try
            {
                double temperature = server.GetTemperature(City);
                if (temperature != NO_DATA)
                {
                    ViewData["temperature"] = temperature;
                    if (City.Length != 1)
                    {
                        City = char.ToUpper(City.ToLower()[0]).ToString() + City.ToLower().Substring(1);
                    } else
                    {
                        City = char.ToUpper(City.ToLower()[0]).ToString();
                    }
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
