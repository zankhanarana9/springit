package com.zrana.springit.config;

import net.bytebuddy.build.ToStringPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="springit")
public class SpringitProperties {
    /**
     * This is our welcome message
     */
    private String welcomeMsg = "Hello, world!";

    public String getWelcomeMessage() {
        return welcomeMsg;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMsg = welcomeMessage;
    }
}
