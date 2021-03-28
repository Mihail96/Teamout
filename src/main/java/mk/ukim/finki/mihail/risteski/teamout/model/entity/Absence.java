package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Absence
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Date DateFrom;

    private Date DateTo;

    @ManyToOne
    private Employee Employee;

    @ManyToOne
    private AbsenceType Type;

    @ManyToOne
    private AbsenceStatus Status;
}
