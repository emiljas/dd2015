using System.Collections.Generic;
using System.Linq;
using Pietryna.API.Database;
using Pietryna.API.ViewModels;

namespace Pietryna.API.Services
{
    public class RateService
    {
        private readonly ddEntities _db;

        public RateService()
        {
            _db = new ddEntities();
        }

        public void ChangeRate(RateViewModel model)
        {
            var rates = _db.rates.Where(r => r.PLACE_ID == model.PlaceId && r.USER_ID == model.UserId);
            var ra = rates.FirstOrDefault();

            if (ra == null)
            {
                _db.rates.Add(new rate {PLACE_ID = model.PlaceId, USER_ID = model.UserId, Rate1 = model.Rate});
            }
            else
            {
                ra.Rate1 = model.Rate;
            }

            _db.SaveChanges();
        }

        public decimal GetMyRate(int placeId, int userId)
        {
            decimal rate = _db.rates
                .Where(r => r.PLACE_ID == placeId && r.USER_ID == userId)
                .Select(r => r.Rate1)
                .FirstOrDefault();

            return rate;
        }

        public decimal GetRate(int placeId)
        {
            List<decimal> rates = _db.rates
                .Where(r => r.PLACE_ID == placeId)
                .Select(r => r.Rate1)
                .ToList();

            if (rates.Count == 0)
            {

                return 0;
            }

            return rates.Average();
        }

        public void AddRate(RateViewModel model)
        {
            var rate = new rate
            {
                PLACE_ID = model.PlaceId,
                USER_ID = model.UserId,
                Rate1 = model.Rate
            };

            _db.rates.Add(rate);
        }
    }
}