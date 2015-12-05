/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author dancinggrass
 */
public class SOAPHeaderHandler implements SOAPHandler<SOAPMessageContext> {
    private String useragent;
    private String ip;
    
    public SOAPHeaderHandler(String useragent, String ip) {
        this.useragent = useragent;
        this.ip = ip;
    }
    public static void registerHeader(service.StackExchangeService port, String useragent, String ip) {
        // HandlerChain installieren
        Binding binding = ((BindingProvider) port).getBinding();
        List hchain = binding.getHandlerChain();
        if (hchain == null) {
          hchain = new ArrayList();
        }
        hchain.add(new SOAPHeaderHandler(useragent, ip));
        binding.setHandlerChain(hchain);
    }
    
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean request = ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

        if (request) {
            @SuppressWarnings("unchecked")
            Map<String, List<String>> headers = (Map<String, List<String>>) context
                    .get(MessageContext.HTTP_REQUEST_HEADERS);
            if (null == headers) {
                headers = new HashMap<String, List<String>>();
            }
            headers.put("User-Agent", Collections.singletonList(this.useragent));
            headers.put("Remote-Origin", Collections.singletonList(this.ip));
            context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
