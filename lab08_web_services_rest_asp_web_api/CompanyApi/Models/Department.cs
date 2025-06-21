using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CompanyApi.Models
{
    [Table("Department")]
    public partial class Department
    {
        public Department()
        {
            Employees = new HashSet<Employee>();
        }

        [Key]
        [Column("DepartmentId")]
        public int DepartmentId { get; set; }

        [StringLength(20)]
        public string Name { get; set; } = null!;

        [InverseProperty("Department")]
        public virtual ICollection<Employee> Employees { get; set; }
    }
} 