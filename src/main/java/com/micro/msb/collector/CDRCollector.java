package com.micro.msb.collector;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

@Component
public class CDRCollector extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(CDRCollector.class);

    @Value("${queue.balancer.name}")
    private String name;    
    @Value("${queue.balancer.router}")
    private String tcpConnect;

    private ZMQ.Context context;
    private ZMQ.Socket requester;
    
    private String fileDirectoryInput;
    private String fileDirectoryOutput;
    private String fileType;
    private String fileExt;

    public void setFileDirectoryInput(String fileDirectoryInput) {
        this.fileDirectoryInput = fileDirectoryInput;
    }

    public void setFileDirectoryOutput(String fileDirectoryOutput) {
        this.fileDirectoryOutput = fileDirectoryOutput;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }
    
    @Override
    public void run() {

        LOG.info("Initiate Queue "+name+"...");

        context = ZMQ.context(1);
        requester = context.socket(ZMQ.REQ);
        requester.connect(tcpConnect);
                
        CDRMessage cdr = new CDRMessage();
        cdr.setFileDirectoryInput(fileDirectoryInput);
        cdr.setFileDirectoryOutput(fileDirectoryOutput);
        cdr.setFileType(fileType);
        cdr.setFileExt(fileExt);
        
        Gson gson = new Gson();
        
        for (int requestNbr = 0; requestNbr < 1; requestNbr++) {
            
            cdr.setFileName("AIROUTPUTCDR_4006_AJKT32_7890_20200709-064233.AIR");
            String message = gson.toJson(cdr);
            requester.send(message.getBytes(), 0);
            byte[] reply = requester.recv(0);
            LOG.info("Received Status : " + new String(reply));
            
        }
        requester.close();
        context.term();
    }
}
