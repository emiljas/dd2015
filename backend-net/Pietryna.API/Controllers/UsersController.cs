namespace Pietryna.API.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using DataModels;
    using Filters;
    using Model;
    using Database;
    using System;
    using System.Text;
    using Services;
    using System.Net;
    using System.Net.Http;

    public class UsersController : ControllerBase
    {
        public IEnumerable<User> GetUsers()
        {
            return Db.Users.ToList();
        }

        [BasicAuthentication]
        [Route("api/users/me")]
        public IHttpActionResult GetMe()
        {
            User user = this.GetAuthenticatedUser();
            if (user == null)
            {
                return this.NotFound();
            }

            return Ok(user);
        }

        [HttpPost]
        [Route("api/users/register")]
        public IHttpActionResult Registration(RegisterParamsModel model)
        {
            string salt = GetSalt();
            var u = new user();
            u.EMAIL = model.Email;
            u.FIRSTNAME = model.FirstName;
            u.LASTNAME = model.LastName;
            u.PASSWORD = GetPassword(model.Password,salt);
            u.photoUrl = model.Photo;
            u.salt = salt;
            u.username = model.UserName;

            var service = new UserService();

            if (service.Check(model.Email, model.UserName))
                return ResponseMessage(Request.CreateErrorResponse(HttpStatusCode.InternalServerError, "Podany e-mail lub login istnieje w bazie"));

            service.AddUser(u);

            return Ok("Success");
        }

        private string GetPassword(string password, string salt)
        {
            string sHashWithSalt = password + salt;
            byte[] saltedHashBytes = Encoding.UTF8.GetBytes(sHashWithSalt);
            System.Security.Cryptography.HashAlgorithm algorithm = new System.Security.Cryptography.SHA256Managed();
            byte[] hash = algorithm.ComputeHash(saltedHashBytes);
            return Convert.ToBase64String(hash);
        }

        private string GetSalt()
        {
            var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            var stringChars = new char[8];
            var random = new Random();

            for (int i = 0; i < stringChars.Length; i++)
            {
                stringChars[i] = chars[random.Next(chars.Length)];
            }

            return new String(stringChars);
        }
    }
}