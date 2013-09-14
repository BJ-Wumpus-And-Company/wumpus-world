package org.bjwumpusandcompany.wumpusworld;

import org.bjwumpusandcompany.wumpusworld.common.Position;
import org.bjwumpusandcompany.wumpusworld.common.Square;

public interface EntityInterface {
	public void updateWorld(Square squares[][]);
	public Position getPosition();
}
