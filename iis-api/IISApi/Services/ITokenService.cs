using IISApi.Models;

namespace IISApi.Services
{
    public interface ITokenService
    {
        string GenerateToken(User user, string key, string issuer, string audience);
    }
}
