package com.frontend.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author xujiping
 * @date 2018/7/4 17:42
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

//    /**
//     * 配置一个TomcatEmbeddedServletContainerFactory bean
//     *
//     * @return
//     */
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    /**
//     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
//     * 但是不能同时在application.properties中同时配置两个connector，
//     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
//     *
//     * @return Connector
//     */
//    private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080); // http端口
//        connector.setSecure(false);
//        connector.setRedirectPort(8011); // application.properties中配置的https端口
//        return connector;
//    }
}
