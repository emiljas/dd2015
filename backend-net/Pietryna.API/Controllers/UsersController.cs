namespace Pietryna.API.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using DataModels;
    using Filters;

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
    }
}