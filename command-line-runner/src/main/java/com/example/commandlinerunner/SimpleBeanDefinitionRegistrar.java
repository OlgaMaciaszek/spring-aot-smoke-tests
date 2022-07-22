package com.example.commandlinerunner;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Stephane Nicoll
 */
public class SimpleBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
			BeanNameGenerator importBeanNameGenerator) {
		String[] types = new String[] { Test.class.getName() };
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SimpleBean.class);
		builder.addConstructorArgValue("test");
		builder.addConstructorArgValue(types);
		registry.registerBeanDefinition("testBean", builder.getBeanDefinition());
	}

}
