using IISApi.Models;

namespace IISApi.Services
{
    public class UserService : IUserService
    {
        private static List<User> Users() => new()
        {
            new User{Username = "user", Password="user" },
            new User {Username = "admin", Password = "admin", Role = "admin" }
        };

        public (bool, string?) IsLoginValid(User user)
        {
            var _user = Users().Where(w => w.Username.Equals(user.Username) && w.Password.Equals(user.Password));
            return (_user.Any(), _user.Select(s => s.Role).FirstOrDefault());
        }
    }
}
