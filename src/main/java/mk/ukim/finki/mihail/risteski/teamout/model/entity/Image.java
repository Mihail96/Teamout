package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    String Name;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    byte[] Content;

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

    public byte[] getContent()
    {
        return Content;
    }

    public void setContent(byte[] content)
    {
        Content = content;
    }
}
