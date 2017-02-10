package com.github.lariscy.jserverrepo.server.dao;

import com.github.lariscy.jserverrepo.server.model.Node;
import java.util.List;

/**
 * @author Steven Lariscy
 */
public interface NodeDao {

    List<Node> getNodes();
    
}
