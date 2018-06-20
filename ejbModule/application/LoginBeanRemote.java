package application;

import javax.ejb.Remote;

@Remote
public interface LoginBeanRemote {
	public int login(String username, String password);
}
