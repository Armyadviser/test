package storm.falcon.injection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import storm.falcon.utils.ReflectUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllTest {

    @Test
    void loadClassTest() {
        ClassMethodLoader.getInstance().load();

        // 资源扫描
//        org.springframework.context.support.AbstractApplicationContext.getResources("")
//        org.springframework.core.io.support.PathMatchingResourcePatternResolver.getResources("");
//        org.springframework.core.io.support.PathMatchingResourcePatternResolver.findPathMatchingResources("");

        // 解析
//        org.springframework.aop.config.ConfigBeanDefinitionParser.parse(Element, ParserContext);
//        org.springframework.aop.config.ConfigBeanDefinitionParser.parsePointcut(Element, ParserContext);
//        org.springframework.aop.config.ConfigBeanDefinitionParser.parseAdvisor(Element, ParserContext);
//        org.springframework.aop.config.ConfigBeanDefinitionParser.parseAspect(Element, ParserContext);
//        org.springframework.aop.config.ConfigBeanDefinitionParser.createAdviceDefinition(
//                adviceElement, parserContext, aspectName, order, methodDef, aspectFactoryDef, beanDefinitions, beanReferences);

        // 调用
//        org.springframework.aop.framework.JdkDynamicAopProxy.invoke();
    }

}