using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CompanyApi.Models
{
    [Table("Employee")]
    public partial class Employee
    {
        public Employee()
        {
            Subordinates = new HashSet<Employee>();
        }

        [Key]
        [Column("EmployeeId")]
        public int EmployeeId { get; set; }

        [StringLength(15)]
        public string FirstName { get; set; } = null!;

        [StringLength(15)]
        public string LastName { get; set; } = null!;

        public int? ManagerId { get; set; }

        [Column(TypeName = "decimal(6, 2)")]
        public decimal? Salary { get; set; }

        [Column(TypeName = "decimal(6, 2)")]
        public decimal? Bonus { get; set; }

        public int? DepartmentId { get; set; }

        [ForeignKey("DepartmentId")]
        [InverseProperty("Employees")]
        public virtual Department? Department { get; set; }

        [ForeignKey("ManagerId")]
        [InverseProperty("Subordinates")]
        public virtual Employee? Manager { get; set; }

        [InverseProperty("Manager")]
        public virtual ICollection<Employee> Subordinates { get; set; }
    }
} 