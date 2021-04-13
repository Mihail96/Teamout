package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import com.lowagie.text.DocumentException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.FileDto;

public interface IReportService
{
    FileDto GetEmpoyeeReport(Long organizationId) throws DocumentException;
    FileDto GetAbsenceReport(Long organizationId) throws DocumentException;
}
