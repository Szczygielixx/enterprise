using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CompanyApi.Models;

namespace CompanyApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DepartmentsController : ControllerBase
    {
        private readonly CompanyDbContext _context;

        public DepartmentsController(CompanyDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<DepartmentDTO>>> GetDepartments()
        {
            return await _context.Departments
                .Select(d => DepartmentToDTO(d))
                .ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<DepartmentDTO>> GetDepartment(int id)
        {
            var department = await _context.Departments.FindAsync(id);

            if (department == null)
            {
                return NotFound();
            }

            return DepartmentToDTO(department);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutDepartment(int id, DepartmentDTO departmentDTO)
        {
            if (id != departmentDTO.DepartmentId)
            {
                return BadRequest();
            }

            var department = await _context.Departments.FindAsync(id);
            if (department == null)
            {
                return NotFound();
            }

            department.Name = departmentDTO.Name;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DepartmentExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        [HttpPost]
        public async Task<ActionResult<DepartmentDTO>> PostDepartment(DepartmentDTO departmentDTO)
        {
            var department = new Department
            {
                Name = departmentDTO.Name
            };

            _context.Departments.Add(department);
            await _context.SaveChangesAsync();

            return CreatedAtAction(
                nameof(GetDepartment),
                new { id = department.DepartmentId },
                DepartmentToDTO(department));
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDepartment(int id)
        {
            var department = await _context.Departments.FindAsync(id);
            if (department == null)
            {
                return NotFound();
            }

            _context.Departments.Remove(department);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DepartmentExists(int id)
        {
            return _context.Departments.Any(e => e.DepartmentId == id);
        }

        private static DepartmentDTO DepartmentToDTO(Department department) =>
            new DepartmentDTO
            {
                DepartmentId = department.DepartmentId,
                Name = department.Name
            };
    }
} 