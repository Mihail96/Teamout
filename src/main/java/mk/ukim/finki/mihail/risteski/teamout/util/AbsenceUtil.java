package mk.ukim.finki.mihail.risteski.teamout.util;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AbsenceUtil
{
    public static AbsenceDto CreateAbsenceDto(Absence absence) throws NotFoundException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        AbsenceDto absenceDto = new AbsenceDto();
        absenceDto.setId(absence.getId());
        absenceDto.setDateFrom(dateFormat.format(absence.getDateFrom()));
        absenceDto.setDateTo(dateFormat.format(absence.getDateTo()));
        absenceDto.setAbsenceStatus(absence.getStatus().getName());
        absenceDto.setAbsenceType(absence.getType().getName());

        Employee employee = absence.getEmployee();
        User user = employee.getUserInOrganization().getUser();
        absenceDto.setEmployeeId(employee.getId());
        absenceDto.setEmployeeFirstName(user.getFirstName());
        absenceDto.setEmployeeLastName(user.getLastName());

        return absenceDto;
    }
}
