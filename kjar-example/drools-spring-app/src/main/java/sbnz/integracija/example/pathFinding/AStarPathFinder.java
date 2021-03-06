package sbnz.integracija.example.pathFinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sbnz.integracija.example.facts.Map;
import sbnz.integracija.example.facts.Position;
import sbnz.integracija.example.facts.Troop;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm
 * to determine a path. 
 * 
 * @author Kevin Glass
 */
public class AStarPathFinder {
	/** The set of nodes that have been searched through */
	private ArrayList closed = new ArrayList();
	/** The set of nodes that we do not yet consider fully searched */
	private SortedList open = new SortedList();
	
	/** The map being searched */
	private Map map;
	/** The maximum depth of search we're willing to accept before giving up */
	private int maxSearchDistance;
	
	/** The complete set of nodes across the map */
	private Node[][] nodes;
	/** True if we allow diaganol movement */
	private boolean allowDiagMovement;
	/** The heuristic we're applying to determine which nodes to search first */
	private AStarHeuristic heuristic;
	
	/**
	 * Create a path finder with the default heuristic - closest to target.
	 * 
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(Map map, int maxSearchDistance) {
		this(map, maxSearchDistance, new ClosestHeuristic());
	}

	/**
	 * Create a path finder 
	 * 
	 * @param heuristic The heuristic used to determine the search order of the map
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(Map map, int maxSearchDistance, AStarHeuristic heuristic) {
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		//this.allowDiagMovement = allowDiagMovement;
		
		nodes = new Node[10][10];
		for (int x=0;x<10;x++) {
			for (int y=0;y<10;y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}
	
	/**
	 * @see PathFinder#findPath(Mover, int, int, int, int)
	 */
	public List<Position> findPath(Map map, int sx, int sy) {
		nodes[sx][sy].cost = 0;
		nodes[sx][sy].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		List<Position> positions = new ArrayList();
		int maxDepth = 0;
		Node current = getFirstInOpen();
		while ((current.depth <= maxSearchDistance) && (open.size() != 0)) {
			removeFromOpen(current);
			addToClosed(current);
			positions.add(new Position(current.x, current.y));
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					if ((x == 0) && (y == 0)) {
						continue;
					}
					int xp = x + current.x;
					int yp = y + current.y;
					
					if (xp < 0 || yp < 0 || xp > 9 || yp > 9) {
						continue;
					}
					
					if (map.checkField(xp,yp)) {
						Node neighbour = nodes[xp][yp];
						map.pathFinderVisited(xp, yp);
						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}
				}
			}
			current = getFirstInOpen();
		}
		System.out.println("ZA: " + sx + " " + sy + ": ");
		int i = 0;
		for (Position p : positions) {
			System.out.println(i + ": " + p.getPosX() + " " + p.getPosY());
			i++;
		}
		System.out.println("---------------------------------------------");
		return positions;
	}
	
	public List<Position> findPathAll(Map map, int sx, int sy) {
		nodes[sx][sy].cost = 0;
		nodes[sx][sy].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		List<Position> positions = new ArrayList();
		int maxDepth = 0;
		Node current = getFirstInOpen();
		while ((current.depth <= maxSearchDistance) && (open.size() != 0)) {
			removeFromOpen(current);
			addToClosed(current);
			positions.add(new Position(current.x, current.y));
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					if ((x == 0) && (y == 0)) {
						continue;
					}
					int xp = x + current.x;
					int yp = y + current.y;
					
					if (xp < 0 || yp < 0 || xp > 9 || yp > 9) {
						continue;
					}
					
					//if (map.checkField(xp,yp)) {
						Node neighbour = nodes[xp][yp];
						map.pathFinderVisited(xp, yp);
						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					//}
				}
			}
			current = getFirstInOpen();
		}
		System.out.println("ZA: " + sx + " " + sy + ": ");
		int i = 0;
		for (Position p : positions) {
			System.out.println(i + ": " + p.getPosX() + " " + p.getPosY());
			i++;
		}
		System.out.println("---------------------------------------------");
		return positions;
	}
	
	public Position findClosestEnemy(int sx, int sy) {
		Position closestEnemy = new Position();
		int minTurns = 0;
		int i = 0;
		for (Troop t : map.getTroops()) {
			if (!t.getisEnemy()) {
				nodes[sx][sy].cost = 0;
				nodes[sx][sy].depth = 0;
				closed.clear();
				open.clear();
				//ubacujemo pocetnu poziciju u open listu
				open.add(nodes[sx][sy]);
				int tx = t.getCurrentPositionX();
				int ty = t.getCurrentPositionY();
				
				int maxDepth = 0;
				while ((maxDepth < 500) && (open.size() != 0)) {
					Node current = getFirstInOpen();
					if (current == nodes[tx][ty]) {
						int turns = current.depth / maxSearchDistance + current.depth % maxSearchDistance;
						if (i == 0) {
							minTurns = turns;
							closestEnemy = new Position(current.getX(), current.getY());
							int back = current.depth;
							while(current.depth > maxSearchDistance) {
								closestEnemy = new Position(current.parent.x, current.parent.y);
								current = current.parent;
							}
//							for (int j=0; j<minTurns; j++) {
//								closestEnemy = new Position(current.parent.x, current.parent.y);
//								current = current.parent;
//							}
						}
						else if (turns < minTurns) {
							minTurns = turns;
							closestEnemy = new Position(current.getX(), current.getY());
							int back = current.depth;
							while(current.depth > maxSearchDistance) {
								closestEnemy = new Position(current.parent.x, current.parent.y);
								current = current.parent;
							}
						}
						break;
					}
					
					removeFromOpen(current);
					addToClosed(current);

					for (int x=-1;x<2;x++) {
						for (int y=-1;y<2;y++) {
							// not a neighbour, its the current tile
							if ((x == 0) && (y == 0)) {
								continue;
							}
							
							// determine the location of the neighbour and evaluate it
							int xp = x + current.x;
							int yp = y + current.y;
							
							if (xp < 0 || yp < 0 || xp > 9 || yp > 9) {
								continue;
							}
							
							//if (map.checkField(xp,yp)) {
								float nextStepCost = current.cost + getMovementCost(current.x, current.y, xp, yp);
								Node neighbour = nodes[xp][yp];
								map.pathFinderVisited(xp, yp);

								if (nextStepCost < neighbour.cost) {
									if (inOpenList(neighbour)) {
										removeFromOpen(neighbour);
									}
									if (inClosedList(neighbour)) {
										removeFromClosed(neighbour);
									}
								}
								
								if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
									neighbour.cost = nextStepCost;
									maxDepth = Math.max(maxDepth, neighbour.setParent(current));
									addToOpen(neighbour);
								}
							//}
						}
					}
				}
				i++;
			}
		}
		return closestEnemy;
		
		// while we haven't found the goal and haven't exceeded our max search depth
		

		// since we've got an empty open list or we've run out of search 
		// there was no path. Just return null
//		if (nodes[tx][ty].parent == null) {
//			return null;
//		}
//		
//		// At this point we've definitely found a path so we can uses the parent
//		// references of the nodes to find out way from the target location back
//		// to the start recording the nodes on the way.
//		Path path = new Path();
//		Node target = nodes[tx][ty];
//		while (target != nodes[sx][sy]) {
//			path.prependStep(target.x, target.y);
//			target = target.parent;
//		}
//		path.prependStep(sx,sy);
//		
//		// thats it, we have our path 
//		return path;
	}
	
	

	
	/**
	 * Get the first element from the open list. This is the next
	 * one to be searched.
	 * 
	 * @return The first element in the open list
	 */
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}
	
	/**
	 * Add a node to the open list
	 * 
	 * @param node The node to be added to the open list
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}
	
	/**
	 * Check if a node is in the open list
	 * 
	 * @param node The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}
	
	/**
	 * Remove a node from the open list
	 * 
	 * @param node The node to remove from the open list
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
	
	/**
	 * Add a node to the closed list
	 * 
	 * @param node The node to add to the closed list
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}
	
	/**
	 * Check if the node supplied is in the closed list
	 * 
	 * @param node The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}
	
	/**
	 * Remove a node from the closed list
	 * 
	 * @param node The node to remove from the closed list
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}
	
	/**
	 * Check if a given location is valid for the supplied mover
	 * 
	 * @param mover The mover that would hold a given location
	 * @param sx The starting x coordinate
	 * @param sy The starting y coordinate
	 * @param x The x coordinate of the location to check
	 * @param y The y coordinate of the location to check
	 * @return True if the location is valid for the given mover
	 */
//	protected boolean isValidLocation(Mover mover, int sx, int sy, int x, int y) {
//		boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());
//		
//		if ((!invalid) && ((sx != x) || (sy != y))) {
//			invalid = map.blocked(mover, x, y);
//		}
//		
//		return !invalid;
//	}
	
	/**
	 * Get the cost to move through a given location
	 * 
	 * @param mover The entity that is being moved
	 * @param sx The x coordinate of the tile whose cost is being determined
	 * @param sy The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The cost of movement through the given tile
	 */
	public float getMovementCost(int sx, int sy, int tx, int ty) {
		return map.getCost(sx, sy, tx, ty);
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which 
	 * order the locations are processed.
	 * 
	 * @param mover The entity that is being moved
	 * @param x The x coordinate of the tile whose cost is being determined
	 * @param y The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The heuristic cost assigned to the tile
	 */
//	public float getHeuristicCost(Mover mover, int x, int y, int tx, int ty) {
//		return heuristic.getCost(map, mover, x, y, tx, ty);
//	}
	
	/**
	 * A simple sorted list
	 *
	 * @author kevin
	 */
	private class SortedList {
		/** The list of elements */
		private ArrayList list = new ArrayList();
		
		/**
		 * Retrieve the first element from the list
		 *  
		 * @return The first element from the list
		 */
		public Object first() {
			return list.get(0);
		}
		
		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}
		
		/**
		 * Add an element to the list - causes sorting
		 * 
		 * @param o The element to add
		 */
		public void add(Object o) {
			list.add(o);
			Collections.sort(list);
		}
		
		/**
		 * Remove an element from the list
		 * 
		 * @param o The element to remove
		 */
		public void remove(Object o) {
			list.remove(o);
		}
	
		/**
		 * Get the number of elements in the list
		 * 
		 * @return The number of element in the list
 		 */
		public int size() {
			return list.size();
		}
		
		/**
		 * Check if an element is in the list
		 * 
		 * @param o The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(Object o) {
			return list.contains(o);
		}
	}
	
	/**
	 * A single node in the search graph
	 */
	private class Node implements Comparable {
		/** The x coordinate of the node */
		private int x;
		/** The y coordinate of the node */
		private int y;
		/** The path cost for this node */
		private float cost;
		/** The parent of this node, how we reached it in the search */
		private Node parent;
		/** The heuristic cost of this node */
		private float heuristic;
		/** The search depth of this node */
		private int depth;
		
		/**
		 * Create a new node
		 * 
		 * @param x The x coordinate of the node
		 * @param y The y coordinate of the node
		 */
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
		/**
		 * Set the parent of this node
		 * 
		 * @param parent The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			
			return depth;
		}
		
		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;
			
			float f = heuristic + cost;
			float of = o.heuristic + o.cost;
			
			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
