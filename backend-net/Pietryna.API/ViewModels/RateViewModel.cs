using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using Newtonsoft.Json;

namespace Pietryna.API.ViewModels
{
    public class RateViewModel
    {
        public int UserId { get; set; }
        public int PlaceId { get; set; }
        public decimal Rate { get; set; }
    }
}