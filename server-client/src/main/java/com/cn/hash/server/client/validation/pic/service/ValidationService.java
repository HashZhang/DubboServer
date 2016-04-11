package com.cn.hash.server.client.validation.pic.service;

import com.cn.hash.server.client.validation.pic.domain.ValidationPic;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by 862911 on 2016/4/11.
 */
@Path("/flyar/validation")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8", MediaType.TEXT_XML + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8"})
public interface ValidationService {
    @GET
    @Path("all")
    public ValidationPic getValidationPicture() throws IOException;
}
