using IISApi.Models;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace IISApi.Services
{
    public class TokenService : ITokenService
    {
        public string GenerateToken(User user, string key, string issuer, string audience)
        {
            var handler = new JwtSecurityTokenHandler();
            var secret = Encoding.ASCII.GetBytes(key);
            var descriptor = new SecurityTokenDescriptor
            {
                Issuer = issuer,
                Audience = audience,
                Subject = new ClaimsIdentity(new[] { new Claim("id", user.Username.ToString()), new Claim(ClaimTypes.Role, user.Role) }),
                Expires = DateTime.UtcNow.AddMinutes(30),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(secret), SecurityAlgorithms.HmacSha256Signature)
            };
            var token = handler.CreateToken(descriptor);
            return handler.WriteToken(token);
        }
    }
}