package com.nayker.chat.publisher;

import com.nayker.chat.event.DictionaryChanged;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DictionaryPublisher {

    private final ApplicationEventPublisher publisher;

    public DictionaryPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishChangedEvent() {
        publisher.publishEvent(new DictionaryChanged());
    }
}
