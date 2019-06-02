package org.service.hi;

import org.service.hi.service.TestApollo;
import org.service.hi.service.TestApolloConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext ac = new AnnotationConfigApplicationContext("org.service.hi"); 
        
        TestApollo apollo = ac.getBean(TestApollo.class);
        TestApolloConfig test = apollo.getApolloConfig();
        System.out.println(test.toStringByValue());
    }
}
