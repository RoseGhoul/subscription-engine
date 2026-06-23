package com.subscriptionengine.quote.infrastructure.adapter.in.web;
import com.subscriptionengine.quote.application.service.QuoteApplicationService;
import com.subscriptionengine.quote.application.mapper.QuoteMapper;
import com.subscriptionengine.quote.domain.model.Quote;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.QuoteApi;
import org.openapitools.model.QuoteCreate;
import org.openapitools.model.QuoteUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class QuoteApiController implements QuoteApi {
    private final QuoteApplicationService quoteApplicationService;
    private final QuoteMapper quoteMapper;

    @Override
    public ResponseEntity<org.openapitools.model.Quote> createQuote(QuoteCreate quoteCreate) {
        Quote domain = quoteMapper.createDtoToDomain(quoteCreate);
        Quote created = quoteApplicationService.createQuote(domain);
        return new ResponseEntity<>(quoteMapper.domainToDto(created), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Quote> retrieveQuote(String id, String fields) {
        Quote domain = quoteApplicationService.getQuote(id);
        return ResponseEntity.ok(quoteMapper.domainToDto(domain));
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Quote>> listQuote(String fields, Integer offset, Integer limit) {
        List<org.openapitools.model.Quote> list = quoteApplicationService.listQuotes().stream()
                .map(quoteMapper::domainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Quote> patchQuote(String id, QuoteUpdate quoteUpdate) {
        Quote domainUpdate = quoteMapper.updateDtoToDomain(quoteUpdate);
        Quote updated = quoteApplicationService.updateQuote(id, domainUpdate);
        return ResponseEntity.ok(quoteMapper.domainToDto(updated));
    }

    @Override
    public ResponseEntity<Void> deleteQuote(String id) {
        quoteApplicationService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
