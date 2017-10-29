package sk.stopangin.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.stopangin.field.Action;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

public interface ActionService<A extends Serializable, T extends Serializable, R> {

    @RequestMapping(path = "perform", method = RequestMethod.GET)
    A performAction(@QueryParam("gameId") Long gameId, @QueryParam("data") String data);

    @RequestMapping(method = RequestMethod.GET)
    Action<A, T, R> getActionForCurrentRound(@QueryParam("gameId") Long gameId);
}
