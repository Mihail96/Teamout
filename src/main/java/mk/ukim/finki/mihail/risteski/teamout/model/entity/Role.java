package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role
{
    @Id
    private Long Id;

    private String Name;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }
}
