package ru.stepanov.annotations;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Iterator;
import java.util.Map;

class MyBatisDaoHandler implements ApplicationContextAware, InitializingBean {
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext;

    public MyBatisDaoHandler() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void afterPropertiesSet() throws Exception {
        Map beans = this.applicationContext.getBeansWithAnnotation(MyBatisDao.class);
        Iterator i$ = beans.values().iterator();

        while(i$.hasNext()) {
            Object bean = i$.next();
            String namespace = this.extractNamespace(bean);
           // this.logger.debug("Found " + bean.getClass() + ". Setting namespace: " + namespace);
            BeanWrapperImpl wrapper = new BeanWrapperImpl(bean);
            wrapper.setPropertyValue("namespace", namespace);
        }

    }

    private String extractNamespace(Object bean) {
        Class clazz = bean.getClass();
        MyBatisDao annotation = (MyBatisDao)clazz.getAnnotation(MyBatisDao.class);
        String namespace = annotation.value();
        if(namespace == null || namespace.isEmpty()) {
            namespace = bean.getClass().getSimpleName();
        }

        return namespace;
    }
}
