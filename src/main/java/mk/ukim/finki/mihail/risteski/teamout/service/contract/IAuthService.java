package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

import javax.security.auth.login.CredentialException;

public interface IAuthService
{
    User Login(String email, String password) throws CredentialException;
}
