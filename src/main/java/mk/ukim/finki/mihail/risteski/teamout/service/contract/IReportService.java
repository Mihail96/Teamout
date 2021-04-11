package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import com.lowagie.text.DocumentException;

public interface IReportService
{
    void GetAbsenceReport(Long organizationId) throws DocumentException;
}
