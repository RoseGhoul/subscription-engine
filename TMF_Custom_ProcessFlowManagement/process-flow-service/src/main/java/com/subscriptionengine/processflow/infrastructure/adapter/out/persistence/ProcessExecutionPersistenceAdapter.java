package com.subscriptionengine.processflow.infrastructure.adapter.out.persistence;
import com.subscriptionengine.processflow.application.port.out.ProcessExecutionPort;
import com.subscriptionengine.processflow.domain.model.ProcessExecution;
import com.subscriptionengine.processflow.infrastructure.adapter.out.persistence.mapper.ProcessExecutionPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class ProcessExecutionPersistenceAdapter implements ProcessExecutionPort {
    private final ProcessExecutionRepository repository;
    private final ProcessExecutionPersistenceMapper mapper;
    @Override
    public ProcessExecution save(ProcessExecution execution) {
        var entity = mapper.domainToEntity(execution);
        var savedEntity = repository.save(entity);
        return mapper.entityToDomain(savedEntity);
    }
    @Override
    public List<ProcessExecution> findAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDomain)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<ProcessExecution> findById(String id) {
        return repository.findById(id).map(mapper::entityToDomain);
    }
}
