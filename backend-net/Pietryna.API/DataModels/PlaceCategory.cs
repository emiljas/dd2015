namespace Pietryna.API.DataModels
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using Newtonsoft.Json;

    [Table("place_category")]
    public class PlaceCategory
    {
        [Key]
        [Column("CATEGORY_ID")]
        public int PlaceCategoryId { get; set; }

        public string Name { get; set; }

        [JsonIgnore]
        public virtual ICollection<Place> Places { get; set; }
    }
}