package sk.stopangin.resource;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Board resource.
 * Created by his majesty jansedlakMBp on 24/10/2017.
 */
@Component
@Path("/board")
public interface BoardResource {

    /**
     * Create board for game.
     * @param request create request.
     * @return {@link javax.ws.rs.core.Response.Status#OK} status if successful.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response createBoard(@Context HttpServletRequest request);
}
