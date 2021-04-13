package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import com.lowagie.text.DocumentException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.FileDto;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "organization/{organizationId}/report")
public class ReportController
{
    private final IReportService _reportService;

    public ReportController(IReportService reportService)
    {
        _reportService = reportService;
    }

    @GetMapping(value = "/employee-report", produces = "application/pdf")
    public void GetEmployeeReport(@PathVariable(value="organizationId")Long organizationId,
                                  HttpServletResponse response) throws DocumentException
    {
        FileDto fileDto = _reportService.GetEmpoyeeReport(organizationId);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileDto.getName());

        try
        {
            response.getOutputStream().write(fileDto.getContent());
            response.flushBuffer();
        } catch (IOException e)
        {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
