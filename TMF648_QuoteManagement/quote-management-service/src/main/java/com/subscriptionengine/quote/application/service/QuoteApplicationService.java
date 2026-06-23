package com.subscriptionengine.quote.application.service;
import com.subscriptionengine.quote.domain.model.Quote;
import com.subscriptionengine.quote.infrastructure.adapter.out.persistence.QuoteRepository;
import com.subscriptionengine.quote.infrastructure.adapter.out.persistence.entity.QuoteJpaEntity;
import com.subscriptionengine.quote.application.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class QuoteApplicationService {
    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;

    @Transactional
    public Quote createQuote(Quote quote) {
        quote.setState("IN_PROGRESS");
        if (quote.getQuoteDate() == null) {
            quote.setQuoteDate(OffsetDateTime.now());
        }
        
        QuoteJpaEntity entity = quoteMapper.domainToEntity(quote);
        entity = quoteRepository.save(entity);
        return quoteMapper.entityToDomain(entity);
    }

    @Transactional(readOnly = true)
    public Quote getQuote(String id) {
        return quoteRepository.findById(id)
                .map(quoteMapper::entityToDomain)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    @Transactional(readOnly = true)
    public List<Quote> listQuotes() {
        return quoteRepository.findAll().stream()
                .map(quoteMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public Quote updateQuote(String id, Quote quoteUpdate) {
        QuoteJpaEntity existing = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        
        quoteMapper.updateEntityFromDomain(quoteUpdate, existing);
        existing = quoteRepository.save(existing);
        return quoteMapper.entityToDomain(existing);
    }

    @Transactional
    public void deleteQuote(String id) {
        quoteRepository.deleteById(id);
    }
}
