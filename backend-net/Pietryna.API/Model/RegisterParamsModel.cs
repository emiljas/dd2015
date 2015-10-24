﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Pietryna.API.Model
{
    public class RegisterParamsModel
    {
        public string UserName { get; set; }
        public string Password { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Alias { get; set; }
        public string Email { get; set; }
        public string Photo { get; set; }
    }
}