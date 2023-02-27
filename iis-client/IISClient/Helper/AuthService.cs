using Newtonsoft.Json.Linq;
using System.Net.Http.Headers;
using System.Text;

namespace IISClient.Helper
{
    public static class AuthService
    {
        private const string URL = "http://localhost:5071/login";
        private static string token = "";

        public static string GetToken() => token;

        public static async Task<string> Login(string username, string password)
        {
            var json = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            HttpClient httpClient = new();
            StringContent httpContent = new StringContent(json, Encoding.UTF8, "application/json");
            try
            {
                var response = await httpClient.PostAsync(URL, httpContent);
                if (response.IsSuccessStatusCode)
                {
                    token = await response.Content.ReadAsStringAsync();
                    if (!String.IsNullOrEmpty(token))
                    {
                        return JObject.Parse(token)["token"].ToString();
                    }
                }
            }
            catch (Exception)
            {
            }

            return "";
        }
    }
}
