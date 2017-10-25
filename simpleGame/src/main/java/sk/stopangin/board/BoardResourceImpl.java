package sk.stopangin.board;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sk.stopangin.resource.BoardResource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by his majesty jansedlakMBp on 25/10/2017.
 */
@Api(value = "Board API")
public class BoardResourceImpl implements BoardResource {

    @Path("/create")
    @ApiOperation(value = "Create Board")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created board.")
    }
    )
    @Override
    public Response createBoard(@Context HttpServletRequest request) {
        return null;
    }
}
