using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Runtime.InteropServices;

namespace Pietryna.API.DataModels
{
    [Table("rates")]
    public class Rates
    {
        [Key]
        [Column("ID")]
        public int Id { get; set; }
        [Column("USER_ID")]
        public int UserId { get; set; }
        [Column("PLACE_ID")]
        public int PlaceId { get; set; }
        [Column("Rate")]
        public decimal Rate { get; set; }

    }
}