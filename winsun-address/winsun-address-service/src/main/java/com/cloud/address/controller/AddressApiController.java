package com.cloud.address.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.address.batch.DBHelper;
import com.cloud.address.es.model.ESAddress;
import com.cloud.address.es.repository.ESAddressRepository;
import com.cloud.address.es.service.ElasticsearchAddressService;
import com.cloud.address.jpa.model.JpaAddress;

@RestController
public class AddressApiController {
	
	@Autowired
	ElasticsearchAddressService addressEsService;
	
	
	@GetMapping("/address/fuzzy")
	public List<ESAddress> query(@RequestParam("address")String address,
			@RequestParam("page")Integer page,
			@RequestParam("size")Integer size){
		
		
		if(page == null) {
			page = 1;
		}
		
		if(size == null){
			size = 10;
		}
		
		
		return addressEsService.addressQuery(address, page, size);
	}
	
//	@GetMapping("/address/comp")
//	public Object comp(@RequestParam("sugtext")String sugtext) {
//		
//		Map<String,Object> msgMap = new HashMap<String,Object>();
//		CompletionSuggestionBuilder completionSuggestionBuilder = new         
//		        CompletionSuggestionBuilder("sugtext");
//		        completionSuggestionBuilder.text(sugtext);
//		        completionSuggestionBuilder.field();
//		        completionSuggestionBuilder.size(3);
//		       
//		        SuggestBuilder suggestBuilder = new SuggestBuilder().addSuggestion("mysite-suggest",completionSuggestionBuilder);
//		        
//		        SearchResponse suggest = es.suggest(suggestBuilder, ESAddress.class);
//		        
//		        List<String> result = new ArrayList<>();
//		       
//		        
//		        List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> entries = suggest.getSuggest()
//		                .getSuggestion("mysite-suggest").getEntries();
//		        
//		        for(Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> op:entries){
//		            List<? extends Option> options = op.getOptions();
//		            for(Suggest.Suggestion.Entry.Option pp : options){
//		                result.add(pp.getText().toString());
//		            }
//		        }
//
//		        msgMap.put("result", result);
//		        return msgMap;
//	}
	
	
	@GetMapping("/address/match")
	public List<ESAddress> match(@RequestParam("address")String address,
			@RequestParam("page")Integer page,
			@RequestParam("size")Integer size){
		
		if(page == null) {
			page = 1;
		}
		
		if(size == null){
			size = 10;
		}
		
		return addressEsService.addressMatch(address, page, size);
	}
	
	
	
	

}
