package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class EmployeeDto
{
    private Long Id;

    private UserDto User;

    public UserDto getUser()
    {
        return User;
    }

    public void setUser(UserDto user)
    {
        User = user;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }
}
