package net.cyberward.springjersey.exception;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Maps a NoResultException to a 404 Response code.
 */
@Component
@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

    private static final Logger logger = LoggerFactory
	    .getLogger(NoResultExceptionMapper.class);

    @Override
    public Response toResponse(NoResultException e) {

	logger.debug(e.getMessage(), e);
	return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }

}
