package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import javassist.NotFoundException;
import lombok.Data;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.Enumeration;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;

import javax.persistence.*;
import java.util.Optional;

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

    public RoleEnum ToRoleEnum() throws NotFoundException
    {
        return RoleEnum.GetById(RoleEnum.None, this.getId());
    }
}
