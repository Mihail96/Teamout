package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import com.lowagie.text.DocumentException;
import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.FileDto;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "organization/{organizationId}/report")
public class ReportController
{
    private final IReportService _reportService;

    public ReportController(IReportService reportService)
    {
        _reportService = reportService;
    }

    @GetMapping
    public String GetEmployeeReport(@PathVariable(value="organizationId")Long organizationId,
                                  Model model) throws DocumentException
    {
        model.addAttribute("bodyContent", "report");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/employee-report", produces = "application/pdf")
    public void GetEmployeeReport(@PathVariable(value="organizationId")Long organizationId,
                                  HttpServletResponse response) throws DocumentException
    {
        HandleReport(response, _reportService.GetEmployeeReport(organizationId));
    }

    @GetMapping(value = "/absence-report", produces = "application/pdf")
    public void GetAbsenceReport(@PathVariable(value="organizationId")Long organizationId,
                                  HttpServletResponse response) throws DocumentException, NotFoundException
    {
        HandleReport(response, _reportService.GetAbsenceReport(organizationId));
    }

    private void HandleReport(HttpServletResponse response, FileDto fileDto)
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileDto.getName());

        try
        {
            response.getOutputStream().write(fileDto.getContent());
            response.flushBuffer();
        }
        catch (IOException e)
        {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
