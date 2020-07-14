package com.yucong.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * beetl配置(如果需要配置别的配置可参照这个形式自己添加)
 */
@Data
@ConfigurationProperties(prefix = BeetlProperties.BEETLCONF_PREFIX)
public class BeetlProperties {

    public static final String BEETLCONF_PREFIX = "beetl";

    private String delimiterStatementStart;

    private String delimiterStatementEnd;

    private String resourceTagroot;

    private String resourceTagsuffix;

    private String resourceAutoCheck;

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    public Properties getProperties() {
        Properties properties = new Properties();
        if (isNotEmpty(delimiterStatementStart)) {
            if (delimiterStatementStart.startsWith("\\")) {
                delimiterStatementStart = delimiterStatementStart.substring(1);
            }
            properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        } else {
        	properties.setProperty("DELIMITER_STATEMENT_START", "@");
        }
        if (isNotEmpty(delimiterStatementEnd)) {
            properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
        } else {
            properties.setProperty("DELIMITER_STATEMENT_END", "null");
        }
        if (isNotEmpty(resourceTagroot)) {
            properties.setProperty("RESOURCE.tagRoot", resourceTagroot);
        }
        if (isNotEmpty(resourceTagsuffix)) {
            properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix);
        }
        if (isNotEmpty(resourceAutoCheck)) {
            properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
        }
        return properties;
    }
    
    
    private boolean isNotEmpty(String str) {
    	return str!=null && str.trim().length()!=0;
    }
}
