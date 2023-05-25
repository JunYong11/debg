package example;

import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import io.reactivex.Flowable;

public class Starter{
	// OPENAI API KEY 설정
	public static String answer = "";
	public static OpenAiService service = new OpenAiService("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");
	public static List<ChatMessage> message = new ArrayList<ChatMessage>();

	public synchronized String input(String input){
		message.add(new ChatMessage(ChatMessageRole.USER.value(), input));

		ChatCompletionRequest chatCompletionRequest;
		chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo").messages(message).n(1)
				.maxTokens(2000).logitBias(Collections.emptyMap()).build();
		// 대화 완료를 스트리밍할 Flowable 개체 만들기
		Flowable<ChatCompletionChunk> flowableResult = service.streamChatCompletion(chatCompletionRequest);
		// 결과를 저장할 StringBuilder 개체 생성
		StringBuilder buffer = new StringBuilder();
		// Flowable 객체를 확인하고 결과를 출력
		flowableResult.subscribe(chunk -> {
			chunk.getChoices().forEach(choice -> {
				String result = choice.getMessage().getContent();
				if (result != null) {
					buffer.append(result);
					answer = choice.getMessage().getContent();
					System.out.print(answer);
				}
			});
		}, Throwable::printStackTrace, () -> System.out.print("\n>>"));
		message.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), buffer.toString()));
		return answer;
	}



	public void close(){
		service.shutdownExecutor();
	}
}