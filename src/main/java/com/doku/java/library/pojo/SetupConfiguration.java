package com.doku.java.library.pojo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SetupConfiguration {
    private String clientId;
    private String key;
    private String environment;
    private String serverLocation;

    public static class SetupConfigurationBuilder {
        public SetupConfigurationBuilder setupServerLocation() {
            if ("sandbox".equals(this.environment)) {
                this.serverLocation = ServerLocation.SANDBOX.getUrl();
            } else if ("production".equals(this.environment)) {
                this.serverLocation = ServerLocation.PRODUCTION.getUrl();
            } else {
                this.serverLocation = this.environment;
            }
            return this;
        }
    }
}
