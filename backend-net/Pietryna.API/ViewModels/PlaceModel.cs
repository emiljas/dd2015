namespace Pietryna.API.ViewModels
{
    using DataModels;

    public class PlaceModel
    {
        public int Id { get; set; }

        public string Category { get; set; }

        public string Description { get; set; }

        public string Address { get; set; }

        public string Email { get; set; }

        public string Phone { get; set; }

        public LocationModel Location { get; set; }

        public User AddedBy { get; set; }
    }
}