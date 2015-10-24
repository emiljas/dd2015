using System;
using Pietryna.API.Services;

namespace Pietryna.API.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using ViewModels;

    public class PlacesController : ControllerBase
    {
        private readonly RateService _service;

        public PlacesController()
        {
            _service = new RateService();
        }

        // GET api/places
        [AllowAnonymous]
        public IEnumerable<PlaceModel> GetPlaces()
        {
            var x = Db.Places.Select(place => new PlaceModel
            {
                Id = place.PlaceId,
                Category = place.Category.Name,
                Description = place.Description,
                Email = place.Email,
                Phone = place.Phone,
                Address = place.Address,
                Location = new LocationModel
                    {
                        Latitude = place.Latitude,
                        Longitude = place.Longitude
                    },
                AddedBy = place.AddedBy
            }).ToList();

            foreach (var l in x)
            {
                l.Rate = _service.GetRate(l.Id);
            }
            return x;
        }

        [Route("api/places/test")]
        public string GetTest()
        {
            return "Test";
        }

        [HttpGet]
        [Route("api/places/get-my-rate")]
        public IHttpActionResult GetMyRate(int placeId, int userId)
        {
            return Ok(_service.GetMyRate(placeId, userId));
        }

        [HttpGet]
        [Route("api/places/get-rate")]
        public IHttpActionResult GetRate(int placeId)
        {
            return Ok(_service.GetRate(placeId));
        }

        [HttpPost]
        [Route("api/places/change-rate")]
        public IHttpActionResult ChangeRate([FromBody]RateViewModel rate)
        {
                _service.ChangeRate(rate);
                return Ok();
        }

        [HttpPost]
        [Route("api/places/add-rate")]
        public IHttpActionResult AddRate(RateViewModel model)
        {
            _service.AddRate(model);
            return Ok();
        }
    }
}