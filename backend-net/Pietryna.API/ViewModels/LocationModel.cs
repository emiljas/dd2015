namespace Pietryna.API.ViewModels
{
    using Newtonsoft.Json;

    public class LocationModel
    {
        [JsonProperty("lat")]
        public float Latitude { get; set; }

        [JsonProperty("lng")]
        public float Longitude { get; set; }
    }
}