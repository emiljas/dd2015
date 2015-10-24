namespace Pietryna.API.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using ViewModels;

    public class PlacesController : ControllerBase
    {
        // GET api/places
        [AllowAnonymous]
        public IEnumerable<PlaceModel> GetPlaces()
        {
            return Db.Places.Select(place => new PlaceModel
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
        }

        [Route("api/places/test")]
        public string GetTest()
        {
            return "Test";
        }
    }
}