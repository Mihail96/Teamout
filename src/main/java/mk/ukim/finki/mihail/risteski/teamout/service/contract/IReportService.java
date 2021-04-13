package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import com.lowagie.text.DocumentException;
import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.FileDto;

public interface IReportService
{
    FileDto GetEmployeeReport(Long organizationId) throws DocumentException;
    FileDto GetAbsenceReport(Long organizationId) throws DocumentException, NotFoundException;
}
