package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.ExampleModel;
import org.springframework.stereotype.Service;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public ExampleModel getEntity(Integer id) {
        return new ExampleModel("hello", id);
    }
}
