package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AbsenceType
{
    @Id
    private Long Id;

    private String Name;
}
