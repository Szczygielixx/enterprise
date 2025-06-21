using Microsoft.EntityFrameworkCore;

namespace CompanyApi.Models
{
    public partial class CompanyDbContext : DbContext
    {
        public CompanyDbContext(DbContextOptions<CompanyDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Department> Departments { get; set; }
        public virtual DbSet<Employee> Employees { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Department>(entity =>
            {
                entity.Property(e => e.Name).IsUnicode(false);
            });

            modelBuilder.Entity<Employee>(entity =>
            {
                entity.Property(e => e.FirstName).IsUnicode(false);
                entity.Property(e => e.LastName).IsUnicode(false);

                entity.HasOne(d => d.Department)
                    .WithMany(p => p.Employees)
                    .HasForeignKey(d => d.DepartmentId)
                    .HasConstraintName("FK_Employee_Department");

                entity.HasOne(d => d.Manager)
                    .WithMany(p => p.Subordinates)
                    .HasForeignKey(d => d.ManagerId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Employee_Employee");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
} 