package com.studyhub.sth.api.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhub.sth.application.events.EntrarReuniaoEvento;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.services.IReuniaoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ReuniaoWsController {
    @Autowired
    IReuniaoService reuniaoService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @MessageMapping("/reunioes.entrar")
//    @SendTo("/topic/reunioes")
    public void teste(WSMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException, JsonProcessingException {
        Usuario usuario = (Usuario) headerAccessor.getSessionAttributes().get("user");
        ObjectMapper objectMapper = new ObjectMapper();
        EntrarReuniaoEvento e = new EntrarReuniaoEvento(UUID.fromString(message.getSalaTematicaId()), usuario.getUsuarioId());
        String json = objectMapper.writeValueAsString(e);

        rabbitTemplate.convertAndSend("reuniaoentrar", json);
//        reuniaoService.entrarReuniaoSalaTematica(UUID.fromString(message.getSalaTematicaId()), usuario);
    }
}
