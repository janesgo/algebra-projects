using IISApi.Models;

namespace IISApi.Services
{
    public interface IUserService
    {
        (bool, string?) IsLoginValid(User user);
    }
}
