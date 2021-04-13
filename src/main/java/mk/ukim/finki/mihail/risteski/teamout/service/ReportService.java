package mk.ukim.finki.mihail.risteski.teamout.service;

import com.lowagie.text.DocumentException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.FileDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IReportService;
import mk.ukim.finki.mihail.risteski.teamout.util.EmployeeUtils;
import mk.ukim.finki.mihail.risteski.teamout.util.ReportUtil;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService implements IReportService
{
    private final AbsenceRepository _absenceRepository;
    private final EmployeeRepository _employeeRepository;

    public ReportService(AbsenceRepository absenceRepository,
                          EmployeeRepository employeeRepository)
    {
        _absenceRepository = absenceRepository;
        _employeeRepository = employeeRepository;
    }

    @Override
    public FileDto GetEmpoyeeReport(Long organizationId) throws DocumentException
    {
        List<Employee> employees = _employeeRepository.GetEmployeesInOrganization(organizationId);

        List<EmployeeDetailsDto> employeeDetailsDtos = new ArrayList<>();
        for (Employee employee: employees)
        {
            employeeDetailsDtos.add(EmployeeUtils.CreateEmployeeDetailsDto(employee));
        }

        Map<String, Object> variablesMap = new HashMap<>();
        variablesMap.put("employeeDetailsDtos", employeeDetailsDtos);

        String html = ReportUtil.ParseReportTemplate("employee-report", variablesMap);

        return new FileDto("Absence report.pdf", GeneratePdf(html));
    }

    @Override
    public FileDto GetAbsenceReport(Long organizationId) throws DocumentException
    {
        List<Absence> absences = _absenceRepository.GetAbsencesInOrganization(organizationId);

        Map<String, Object> variablesMap = new HashMap<>();
        variablesMap.put("absences", absences);

        String html = ReportUtil.ParseReportTemplate("absence-report", variablesMap);

        return new FileDto("Absence report.pdf", GeneratePdf(html));
    }

    private byte[] GeneratePdf(String html) throws DocumentException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);

        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }
}
