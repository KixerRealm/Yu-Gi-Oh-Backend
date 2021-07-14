package com.yugioh.yugioh.services.clients;

import com.google.common.collect.Lists;
import com.querydsl.core.util.FileUtils;
import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.dtos.ygo.YGOCollection;
import com.yugioh.yugioh.exceptions.YugiohException;
import com.yugioh.yugioh.exceptions.YugiohExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class YGOClient {

	private final String BASE_PATH = "https://db.ygoprodeck.com/api/v7/";
	private final RestTemplate restTemplate;

	private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

	private final RequestCallback imageRequestCallback;
	private final String SMALL_IMAGE_BASE_PATH = "https://storage.googleapis.com/ygoprodeck.com/pics_small/";
	private final String BIG_IMAGE_BASE_PATH = "https://storage.googleapis.com/ygoprodeck.com/pics/";
	@Value("${local.small-image.path}")
	private String smallImagesPath;
	@Value("${local.big-image.path}")
	private String bigImagesPath;

	public YGOClient(RestTemplate restTemplate, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.restTemplate = restTemplate;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;

		imageRequestCallback = (ClientHttpRequest request) -> {
			HttpHeaders headers = request.getHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
		};
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

	private static void checkDownload(CompletableFuture<Boolean> future) {
		try {
			if (!future.get()) {
				throw new YugiohException(YugiohExceptionType.FAILED_DOWNLOAD);
			}
		} catch (InterruptedException | ExecutionException e) {
			throw new YugiohException(YugiohExceptionType.FAILED_DOWNLOAD);
		}
	}

	public void downloadSmallImages(List<String> ids) throws IOException {
		multiThreadedDownload(ids, smallImagesPath, SMALL_IMAGE_BASE_PATH);
	}

	public void downloadBigImages(List<String> ids) throws IOException {
		multiThreadedDownload(ids, bigImagesPath, BIG_IMAGE_BASE_PATH);
	}

	public void multiThreadedDownload(List<String> ids, String localPath, String serverPath) throws IOException {
		Path directory = Paths.get(localPath);

		if (Files.exists(directory) && Files.isDirectory(directory)) {
			FileUtils.delete(directory.toFile());
		}
		Files.createDirectories(directory);
		ArrayList<CompletableFuture<Boolean>> futures = Lists.newArrayListWithCapacity(ids.size());
		ids.forEach(id -> futures.add(CompletableFuture.supplyAsync(() -> downloadFile(id, localPath, serverPath), threadPoolTaskExecutor)));
		futures.forEach(YGOClient::checkDownload);
	}

	public boolean downloadFile(String id, String localPath, String serverPath) {
		ResponseExtractor<Boolean> imageExtractor = response -> {
			try {
				Files.copy(response.getBody(), Paths.get(String.format("%s/%s.jpg", localPath, id)));
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		};

		Boolean result = restTemplate.execute(String.format("%s%s.jpg", serverPath, id), HttpMethod.GET, imageRequestCallback, imageExtractor);
		if (result != null) {
			return result;
		}

		return false;
	}
}
