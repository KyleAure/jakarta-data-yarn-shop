package io.openliberty.repositories;

import java.util.List;
import java.util.Optional;

import static jakarta.data.repository.By.ID;
import static io.openliberty.models._Pattern.AUTHOR;

import io.openliberty.entities.Pattern;
import io.openliberty.providers.PatternExtension;
import jakarta.data.Limit;
import jakarta.data.Sort;
import jakarta.data.page.CursoredPage;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

@Repository(provider = PatternExtension.PROVIDER)
public interface Patterns extends DataRepository<Pattern, Long> {
    @Find
    Optional<Pattern> findPattern(@By(ID) Long id);
    
    @Find
    List<Pattern> findAuthoredWorks(@By(AUTHOR) String author);
    
    @Find
    Pattern[] patternByAuthor(@By(AUTHOR) String author, Limit limit, Sort<Pattern> sort);
    
    @Find
    Page<Pattern> patternByAuthor(@By(AUTHOR) String author, PageRequest<Pattern> request);
    
    @Find
    CursoredPage<Pattern> patternByAuthorCursor(@By(AUTHOR) String author, PageRequest<Pattern> request);
}
