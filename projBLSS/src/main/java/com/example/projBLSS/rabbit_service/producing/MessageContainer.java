package com.example.projBLSS.rabbit_service.producing;

import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageContainer {

    private ConcurrentLinkedQueue<PictureToStatsServerDTO> queue;

    public MessageContainer() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<PictureToStatsServerDTO> getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedQueue<PictureToStatsServerDTO> queue) {
        this.queue = queue;
    }
}
