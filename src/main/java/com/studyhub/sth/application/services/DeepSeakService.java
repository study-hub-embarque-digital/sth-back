//package com.studyhub.sth.application.services;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.studyhub.sth.application.dtos.deepseak.ChatRequest;
//import com.studyhub.sth.application.dtos.deepseak.ChatResponse;
//import com.studyhub.sth.application.dtos.deepseak.RoomIADto;
//import com.studyhub.sth.libs.ai.AIClient;
//import com.studyhub.sth.libs.ai.builders.PromptBuilder;
//import com.studyhub.sth.libs.ai.builders.deepseak.DeepSeakPromptBuilder;
//import com.studyhub.sth.libs.ai.messages.PromptMessage;
//import com.studyhub.sth.libs.ai.messages.PromptMessageRole;
//import com.studyhub.sth.libs.ai.messages.PromptResponse;
//import com.studyhub.sth.libs.ai.messages.PromptResponseFormat;
//import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptMessage;
//import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptResponse;
//import com.studyhub.sth.libs.ai.providers.DeepSeakProvider;
//import lombok.Data;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DeepSeakService {
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    public DSPromptResponse sendRequest() throws JsonProcessingException {
////        String url = "https://api.deepseek.com/chat/completions";
////        String t = "```json";
////        String t2 = "```";
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.set("Content-Type", "application/json");
////        headers.set("Authorization", "Bearer sk-4dba111b2eff4bb5a107479799b2af08");
////
////        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
////
////        RestTemplate restTemplate = new RestTemplate();
////        ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(
////                url, HttpMethod.POST, entity, ChatResponse.class);
////
////        String json = responseEntity.getBody().choices.get(0).message.content
////                .replaceAll(t, "")
////                .replaceAll(t2, "");
////
////        RoomIADto room = objectMapper.readValue(json, RoomIADto.class);
////
////
////        return room;
//        List<PromptMessage> messages = new ArrayList<>();
//        messages.add(new DSPromptMessage("voce vai responder tudo apenas com sim ou nao", PromptMessageRole.system));
//        messages.add(new DSPromptMessage("voce ja viu o filme matrix?", PromptMessageRole.user));
//        AIClient client = new AIClient(new DeepSeakProvider("sk-4dba111b2eff4bb5a107479799b2af08"));
//        DeepSeakPromptBuilder.DeepSeakPromptBuilderBuilder<?, ?> deepSeakPromptBuilder = DeepSeakPromptBuilder.builder()
//                .messages(messages)
//                .model("deepseek-chat")
//                .frequencyPenalty(0)
//                .maxTokens(8192)
//                .presencePenalty(0)
//                .responseFormat(new PromptResponseFormat("text")) // ou crie um builder p/ ResponseFormat
//                .stop(null)
//                .stream(false)
//                .streamOptions(null)
//                .temperature(1)
//                .topP(1)
//                .tools(null)
//                .toolChoice("none")
//                .logprobs(false)
//                .topLogprobs(null);
//
//        return (DSPromptResponse) client.send(deepSeakPromptBuilder.build());
//    }
//}
//
