package com.knewin.service;

import com.knewin.modal.FormDataNews;
import com.knewin.modal.ResultPage;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@RegisterRestClient
public interface NewsService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    ResultPage buscar(@MultipartForm FormDataNews formDataNews);
}
