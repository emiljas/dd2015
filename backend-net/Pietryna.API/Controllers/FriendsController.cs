namespace Pietryna.API.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using System.Web.Http.Description;
    using DataModels;
    using Filters;

    public class FriendsController : ControllerBase
    {
        public IEnumerable<User> GetFriendships()
        {
            User user = this.GetAuthenticatedUser();
            ICollection<User> userFriends = user.Friends;
            return userFriends;
        }

        [ResponseType(typeof (User))]
        [AcceptVerbs("POST")]
        public IHttpActionResult PostFriendship([FromBody] User friendUser)
        {
            if (!this.ModelState.IsValid)
            {
                return this.BadRequest(this.ModelState);
            }

            User user = this.GetAuthenticatedUser();
            User friend = this.Db.Users.SingleOrDefault(u => u.UserId == friendUser.UserId);

            if (friend == null)
            {
                return NotFound();
            }

            user.Friends.Add(friend);
            this.Db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = friend.UserId }, friend);
        }

        [AcceptVerbs("DELETE")]
        public IHttpActionResult DeleteFriendship(int id)
        {
            User user = this.GetAuthenticatedUser();
            User friend = user.Friends.FirstOrDefault(x => x.UserId == id);
            
            if (friend == null)
            {
                return NotFound();
            }

            user.Friends.Remove(friend);
            this.Db.SaveChanges();

            return Ok();
        }
    }
}