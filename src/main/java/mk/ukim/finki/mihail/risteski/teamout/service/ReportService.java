package mk.ukim.finki.mihail.risteski.teamout.service;

import com.lowagie.text.DocumentException;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IReportService;
import mk.ukim.finki.mihail.risteski.teamout.util.ReportUtil;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    public void GetAbsenceReport(Long organizationId) throws DocumentException
    {
        List<Absence> absences = _absenceRepository.GetAbsencesInOrganization(organizationId);

        Map<String, Object> variablesMap = new HashMap<>();
        variablesMap.put("absences", absences);

        String html = ReportUtil.ParseReportTemplate("absence-report", variablesMap);
        byte[] reportContent = GeneratePdf(html);
        String reportName = "Absence report.pdf";


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
