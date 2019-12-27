package iastate.edu.Notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnWebApplication
@Configuration
public class NotificationConfig 
{
	@Bean
	public CustomNotificationConfigurator customNotificationConfigurator() 
	{
		return new CustomNotificationConfigurator();
	}
}
