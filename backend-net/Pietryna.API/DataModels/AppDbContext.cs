namespace Pietryna.API.DataModels
{
    using System.Data.Entity;

    public class AppDbContext : DbContext
    {
        public AppDbContext()
            : base("DeveloperDayConnectionString")
        {
        }

        public DbSet<Place> Places { get; set; }

        public DbSet<User> Users { get; set; }

        public DbSet<PlaceCategory> PlaceCategories { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>().HasMany(m => m.Friends).WithMany().Map(m =>
            {
                m.MapLeftKey("USER_ID");
                m.MapRightKey("FRIEND_ID");
                m.ToTable("person_friendship");
            });
        }
    }
}