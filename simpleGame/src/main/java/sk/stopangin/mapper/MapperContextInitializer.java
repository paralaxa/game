package sk.stopangin.mapper;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class MapperContextInitializer extends ConfigurableMapper {
    @Autowired
    private ApplicationContext applicationContext;
    private MapperFactory factory;

    public MapperContextInitializer() {
        super(false);
    }

    @PostConstruct
    public void initialized() {
        init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;
        appendAllMapperComponentsFromContext();
    }

    private void appendAllMapperComponentsFromContext() {
        Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (Mapper mapper : mappers.values()) {
            addMapper(mapper);
        }
    }

    public void addMapper(Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .customize((Mapper) mapper)
                .register();
    }
}
