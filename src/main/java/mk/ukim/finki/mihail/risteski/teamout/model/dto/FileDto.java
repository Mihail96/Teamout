package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class FileDto
{
    private String Name;
    private byte[] Content;

    public FileDto(String name, byte[] content)
    {
        Name = name;
        Content = content;
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
