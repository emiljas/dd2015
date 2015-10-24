namespace Pietryna.API.DataModels
{
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using Newtonsoft.Json;

    [Table("place")]
    public class Place
    {
        [Key]
        [Column("PLACE_ID")]
        public int PlaceId { get; set; }

        public string Name { get; set; }

        public string Address { get; set; }

        public string Email { get; set; }

        public string Phone { get; set; }

        public string Description { get; set; }

        public float Latitude { get; set; }

        public float Longitude { get; set; }

        [Column("CATEGORY_ID")]
        public int CategoryId { get; set; }

        [Column("ADDED_BY_USER_ID")]
        public int AddedById { get; set; }
        
        [ForeignKey("AddedById")]
        public virtual User AddedBy { get; set; }

        [ForeignKey("CategoryId")]
        public virtual PlaceCategory Category { get; set; }
    }
}