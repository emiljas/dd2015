namespace Pietryna.API.Controllers
{
    using System.Linq;
    using System.Web.Http;
    using DataModels;
    using Filters;
    using log4net;

    [HandleException]
    public abstract class ControllerBase : ApiController
    {
        protected readonly ILog Log = LogManager.GetLogger(typeof(UsersController).Name);

        protected readonly AppDbContext Db;

        protected ControllerBase()
        {
            this.Db = new AppDbContext();
        }

        protected User GetAuthenticatedUser()
        {
            User user = this.Db.Users.FirstOrDefault(u => u.Email == this.User.Identity.Name);
            return user;
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                Db.Dispose();
            }

            base.Dispose(disposing);
        }
    }
}