package com.nayker.chat.listener;


import com.nayker.chat.event.DictionaryChanged;
import com.nayker.chat.service.MessageService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DictionaryListener {

    private final MessageService messageService;

    public DictionaryListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventListener
    @Async
    public void handleChangedEvent(DictionaryChanged event) {
        messageService.invalidateMessages();
    }
}
