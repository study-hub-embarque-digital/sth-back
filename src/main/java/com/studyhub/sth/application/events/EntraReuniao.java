package com.studyhub.sth.application.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhub.sth.domain.before.services.IReuniaoService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntraReuniao {
    private IReuniaoService reuniaoService;

    public EntraReuniao(IReuniaoService reuniaoService) {
        this.reuniaoService = reuniaoService;
    }

    @Bean
    public Queue myQueue() {
        return new Queue("reuniaoentrar");
    }

    @RabbitListener(queues = "reuniaoentrar")
    public void entrarReuniao(String entrarReuniaoEvento) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        EntrarReuniaoEvento eObject = objectMapper.readValue(entrarReuniaoEvento, EntrarReuniaoEvento.class);

        reuniaoService.entrarReuniaoSalaTematica(eObject.getSalaTematicaId(), eObject.getUsuarioAtual());
    }
}
