package org.example;

import java.time.Duration;
import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

public class ChatGPTHelper {
    OpenAiService service;
    public ChatGPTHelper() {
        //Insert token from your OpenAI account 
        service = new OpenAiService("", Duration.ofSeconds(30));
    }
    
    public String getBreakfastIdea(List<String> products) {
        String allProducts = String.join(", ", products);
        String question = "I have " + allProducts + "in Fridge.What can i eat for breakfast? give me 3 ideas.";

        return askChatGPT(question);
    }

    public String getLunchIdea(List<String> products) {
        String allProducts = String.join(", ", products);
        String question = "I have " + allProducts + "in Fridge.What can i eat for lunch? give me 3 ideas.";

        return askChatGPT(question);
    }

    public String getHealthyProductsRecommendation(List<String> products) {
        String allProducts = String.join(", ", products);
        String question = "I have " + allProducts + "in Fridge, can you recommend me what healthy product's can i buy additionally.";
        
        return askChatGPT(question);
    }

    private String askChatGPT(String question) {
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
            .messages(List.of(new ChatMessage("user", question)))
            .model("gpt-3.5-turbo")
            .build();
        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();

        StringBuilder stringBuilder = new StringBuilder();

        choices.stream()
            .map(ChatCompletionChoice::getMessage)
            .map(ChatMessage::getContent)
            .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

}
