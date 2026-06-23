package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence;
import com.subscriptionengine.processflow.application.port.out.ProcessFlowPort;
import com.subscriptionengine.processflow.domain.model.ProcessFlow;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.mapper.ProcessFlowPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class ProcessFlowPersistenceAdapter implements ProcessFlowPort {
    private final ProcessFlowRepository repository;
    private final ProcessFlowPersistenceMapper mapper;
    @Override
    public ProcessFlow save(ProcessFlow processFlow) {
        var entity = mapper.domainToEntity(processFlow);
        var savedEntity = repository.save(entity);
        return mapper.entityToDomain(savedEntity);
    }
    @Override
    public List<ProcessFlow> findAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDomain)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<ProcessFlow> findById(String id) {
        return repository.findById(id).map(mapper::entityToDomain);
    }
}
