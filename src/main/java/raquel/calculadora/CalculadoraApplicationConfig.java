package raquel.calculadora;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculadoraApplicationConfig {

    @Bean
    public TracerImpl tracer(){
        return new TracerImpl();
    }
}
