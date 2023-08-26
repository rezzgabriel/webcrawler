package com.knewin.modal;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FormDataNews {

    @FormParam("action")
    @PartType(MediaType.TEXT_PLAIN)
    public String action;
    @FormParam("page")
    @PartType(MediaType.TEXT_PLAIN)
    public String page;
    @FormParam("currentday")
    @PartType(MediaType.TEXT_PLAIN)
    public String currentDay;
    @FormParam("order")
    @PartType(MediaType.TEXT_PLAIN)
    public String order;
}
