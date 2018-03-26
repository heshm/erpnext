/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.erpnext.framework.config;

import javax.sql.DataSource;

import org.flowable.engine.common.runtime.Clock;
import org.flowable.engine.common.impl.util.DefaultClockImpl;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.IdmManagementService;
import org.flowable.idm.engine.IdmEngine;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.ServiceImpl;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.idm.spring.authentication.SpringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = {
    "org.flowable.idm.extension.conf", // For custom configuration classes
    "org.flowable.idm.extension.bean" // For custom beans
})
public class FlowableIdmEngineConfiguration {

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected PlatformTransactionManager transactionManager;

    @Autowired
    protected Environment environment;

    @Qualifier("idmIdentityService")
    @Autowired(required = false)
    protected IdmIdentityService idmIdentityService;

    @Bean(name = "idmEngine")
    public IdmEngine idmEngine() {
        return idmEngineConfiguration().buildIdmEngine();
    }

    @Bean(name = "idmEngineConfiguration")
    public IdmEngineConfiguration idmEngineConfiguration() {
        SpringIdmEngineConfiguration idmEngineConfiguration = new SpringIdmEngineConfiguration();
        idmEngineConfiguration.setDataSource(dataSource);
        idmEngineConfiguration.setDatabaseSchemaUpdate(IdmEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        idmEngineConfiguration.setTransactionManager(transactionManager);
        /*if (idService != null) {
            idmEngineConfiguration.setIdmIdentityService(idService);
        }*/
        
        idmEngineConfiguration.setIdmIdentityService(idmIdentityService);
        idmEngineConfiguration.setPasswordEncoder(new SpringEncoder(passwordEncoder()));
        return idmEngineConfiguration;
    }

    @Bean(name = "clock")
    @DependsOn("idmEngine")
    public Clock getClock() {
        return idmEngineConfiguration().getClock();
    }

    @Bean(name = "idmIdentityService")
    public IdmIdentityService idmIdentityService() {
        return idmEngine().getIdmIdentityService();
    }

    @Bean
    public IdmManagementService idmManagementService() {
        return idmEngine().getIdmManagementService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encoderConfig = environment.getProperty("security.passwordencoder", String.class, "");
        if ("spring_bcrypt".equalsIgnoreCase(encoderConfig)) {
            return new BCryptPasswordEncoder();
        } else {
            return NoOpPasswordEncoder.getInstance();
        }
    }
}
