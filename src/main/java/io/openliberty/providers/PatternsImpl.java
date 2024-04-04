package io.openliberty.providers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.openliberty.entities.Pattern;
import io.openliberty.models._Pattern;
import io.openliberty.repositories.Patterns;
import jakarta.data.Limit;
import jakarta.data.Sort;
import jakarta.data.page.CursoredPage;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.page.impl.CursoredPageRecord;
import jakarta.data.page.impl.PageRecord;

// Mock a RESTful repository implementation with one backed by an in-memory HashSet

public class PatternsImpl implements Patterns  {
	
	private static final HashSet<Pattern> data = new HashSet<>();
	
	static {
		try {
			data.add(new Pattern(01L, "Rib Vault", 			"WestKnits", 0.00, new URL("www.westknits.com")));
			data.add(new Pattern(02L, "Stripeometry", 		"WestKnits", 0.10, new URL("www.westknits.com")));
			data.add(new Pattern(03L, "Brioche Break", 		"WestKnits", 1.50, new URL("www.westknits.com")));
			data.add(new Pattern(04L, "Faded Undulation", 	"WestKnits", 1.60, new URL("www.westknits.com")));
			data.add(new Pattern(05L, "Stormy Stream", 		"WestKnits", 2.00, new URL("www.westknits.com")));
			data.add(new Pattern(06L, "Striation Valley", 	"WestKnits", 2.50, new URL("www.westknits.com")));
			data.add(new Pattern(07L, "Treasure Trove", 	"WestKnits", 2.60, new URL("www.westknits.com")));
			data.add(new Pattern(10L, "Woolly Waffle", 		"WestKnits", 3.00, new URL("www.westknits.com")));
			data.add(new Pattern(11L, "Geogradiant", 		"WestKnits", 3.10, new URL("www.westknits.com")));
			data.add(new Pattern(12L, "Spiral Splash", 		"WestKnits", 3.50, new URL("www.westknits.com")));
		} catch (MalformedURLException e) {
			//TODO
		}
	}

	@Override
	public Optional<Pattern> findPattern(Long id) {
		return data.stream().filter(pattern -> pattern.id() == id).findFirst();
	}

	@Override
	public List<Pattern> findAuthoredWorks(String author) {
		return data.stream().filter(pattern -> pattern.author().equalsIgnoreCase(author)).collect(Collectors.toList());
	}
	
	private Comparator<Pattern> getComparatorForSort(Sort<? super Pattern> sort) {
		Comparator<Pattern> c = null;
		
		switch(sort.property()) {
		case _Pattern.ID:
			c = Comparator.comparingLong(Pattern::id);
			break;
		case _Pattern.NAME:
			c = Comparator.comparing(Pattern::name);
			break;
		case _Pattern.AUTHOR:
			c = Comparator.comparing(Pattern::author);
			break;
		case _Pattern.COST:
			c = Comparator.comparingDouble(Pattern::cost);
			break;
		default:
			c = Comparator.comparingLong(Pattern::id);
			break;
		}
		
		return sort.isAscending() ? c : c.reversed();
	}

	@Override
	public Pattern[] patternByAuthor(String author, Limit limit, Sort<Pattern> sort) {
		return findAuthoredWorks(author).stream()
				.sorted(getComparatorForSort(sort))
				.limit(limit.maxResults())
				.toArray(Pattern[]::new); 
	}

	@Override
	public Page<Pattern> patternByAuthor(String author, PageRequest<Pattern> request) {
		int end = (int) request.page() * request.size();
		int start = end - request.size();
		
		int size = findAuthoredWorks(author).size();
		int trueEnd = Math.min(end, size);
		
		Comparator<Pattern> c = null;
		for(Sort<? super Pattern> s : request.sorts()) {
			if(c == null) {
				c = getComparatorForSort(s);
				continue;
			}
			c.thenComparing(getComparatorForSort(s));
		}
		
		
		List<Pattern> content = findAuthoredWorks(author).stream()
				.sorted(c)
				.collect(Collectors.toList())
				.subList(start, trueEnd);
		
		return new PageRecord<Pattern>(request, content, content.size(), trueEnd != size);
	}

	@Override
	public CursoredPage<Pattern> patternByAuthorCursor(String author, PageRequest<Pattern> request) {
		//TODO Implement
		return null;
	}

}
