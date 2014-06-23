package portalxpert.common.config;

import java.io.IOException;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * Portalxpert Global Propeties Loading
 * 
 * @author crossent
 *
 */
public class PortalxpertInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext>{

	@Override
	public void initialize(ConfigurableWebApplicationContext ctx) {
		try {
			ctx.getEnvironment().getPropertySources().addLast(new ResourcePropertySource("portalxpertInitializer", "classpath:/portalxpert/config/portalxpert.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
