package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            ProtoTypeBean.class);

        System.out.println("find prototypeBean1");
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find prototypeBean2");
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);
        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);

        protoTypeBean1.destroy();
        protoTypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
