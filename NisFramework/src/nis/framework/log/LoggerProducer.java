package nis.framework.log;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named("log")
@Dependent
public class LoggerProducer {

	@Produces
    public Log getLog(InjectionPoint ip){
        return LogFactory.getLog(ip.getMember().getDeclaringClass().getName());
    }

}