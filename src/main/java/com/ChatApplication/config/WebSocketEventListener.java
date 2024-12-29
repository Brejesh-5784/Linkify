package com.ChatApplication.config;

import com.ChatApplication.chat.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        if (headerAccessor.getSessionAttributes() != null) {
            String username = (String) headerAccessor.getSessionAttributes().get("username");

            if (username != null) {
                log.info("User '{}' has disconnected.", username);

                // Create a ChatMessage instance
                ChatMessage chatMessage = ChatMessage.builder()
                        .sender("user1")
                        .content("Hello World!")
                        .type("CHAT")
                        .build();


                messagingTemplate.convertAndSend("/topic/public", chatMessage);
            } else {
                log.warn("Disconnect event with no username found in session attributes.");
            }
        } else {
            log.warn("Session attributes are null during a disconnect event.");
        }
    }
}
