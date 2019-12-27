package iastate.edu.User;

/**
 * Additional object for admin-related data
 */
public class isAdminForm 
{
	private boolean isAdmin;

	public isAdminForm(boolean admin) 
	{
		isAdmin = admin;
	}

	public boolean isAdmin() 
	{
		return isAdmin;
	}

	public void setAdmin(boolean admin) 
	{
		isAdmin = admin;
	}
}
