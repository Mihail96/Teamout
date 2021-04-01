package mk.ukim.finki.mihail.risteski.teamout.service.contract;

public interface IEmailService
{
    void SendSimpleMessage(String to, String subject, String text);
}
