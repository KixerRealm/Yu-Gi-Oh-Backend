package com.yugioh.yugioh.services.clients;

import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.dtos.ygo.YGOCollection;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class YGOClient {

	private final String BASE_PATH = "https://db.ygoprodeck.com/api/v7/";
	private final RestTemplate restTemplate;

	public YGOClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<YGOCard> getAllCards() {
		String getAllCardInfoPath = "cardinfo.php?format=Speed Duel";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<YGOCollection> result = restTemplate.exchange(BASE_PATH + getAllCardInfoPath, HttpMethod.GET, entity, YGOCollection.class);
		return Objects.requireNonNull(result.getBody()).getData();
	}
}
