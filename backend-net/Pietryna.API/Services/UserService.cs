using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Pietryna.API.Database;

namespace Pietryna.API.Services
{
    public class UserService
    {
        private readonly ddEntities _db;

        public UserService()
        {
            _db = new ddEntities();
        }

        public bool Check(string emial, string username)
        {
            return _db.users.Any(r => r.EMAIL == emial || r.username == username);
        }

        public void AddUser(user u)
        {
            _db.users.Add(u);
            _db.SaveChanges();
        }
    }
}