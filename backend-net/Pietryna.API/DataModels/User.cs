namespace Pietryna.API.DataModels
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using Newtonsoft.Json;

    [Table("user")]
    public class User
    {
        [Key]
        [Column("USER_ID")]
        [JsonProperty("Id")]
        public int UserId { get; set; }

        public string Email { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        [JsonIgnore]
        public virtual ICollection<User> Friends { get; set; }

        [JsonIgnore]
        public virtual ICollection<Place> AddedPlaces { get; set; }
    }
}