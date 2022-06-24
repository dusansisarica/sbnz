package sbnz.integracija.example;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.facts.Creature;
import sbnz.integracija.example.facts.Map;
import sbnz.integracija.example.facts.Position;
import sbnz.integracija.example.facts.Troop;
import sbnz.integracija.example.pathFinding.AStarPathFinder;

@Service
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	@Autowired
	public SampleAppService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
		
	public void testService() {
		KieSession kieSession = kieContainer.newKieSession();
		Creature c = new Creature("dusan", 10, 12, 12, 10, 2, true);
		Creature c1 = new Creature("dj", 12, 10, 12, 10, 3, false);
		Creature c2 = new Creature("kf", 10, 10, 10, 10, 5, true);
		Creature c3 = new Creature("kk", 10, 10, 10, 10, 3, false);
		Creature c4 = new Creature("c4", 10, 10, 10, 10, 2, false);
		
//		Position post1 = new Position(1,0);
//		Position post2 = new Position(0,1);
//		Position post3 = new Position(0,2);
//		List<Position> post = new ArrayList<>();
//		post.add(post1);
//		post.add(post2);
//		post.add(post3);
		Troop t = new Troop(c, 8, new Position(1,1), false);
//		Position post11 = new Position(2,0);
//		Position post12 = new Position(3,1);
//		Position post13 = new Position(3,2);
//		List<Position> pos1t = new ArrayList<>();
//		pos1t.add(post11);
//		pos1t.add(post12);
//		pos1t.add(post13);
		Troop t1 = new Troop(c1, 10, new Position(3,0), false);
		
//		Position post21 = new Position(0,1);
//		Position post22 = new Position(0,0);
//		Position post23 = new Position(2,1);
//		Position post24 = new Position(3,1);
//		Position post25 = new Position(3,0);
//		List<Position> pos2t = new ArrayList<>();
//		pos2t.add(post21);
//		pos2t.add(post22);
//		pos2t.add(post23);
//		pos2t.add(post24);
//		pos2t.add(post25);
		Troop t2 = new Troop(c2, 8, new Position(1,1), true);
		
//		Position post31 = new Position(3,1);
//		Position post32 = new Position(3,0);
//		List<Position> pos3t = new ArrayList<>();
//		pos3t.add(post31);
//		pos3t.add(post32);
		Troop t3 = new Troop(c3, 10, new Position(3,2), true);
		
//		Position post41 = new Position(5,1);
//		Position post42 = new Position(5,3);
//		List<Position> pos4t = new ArrayList<>();
//		pos4t.add(post41);
//		pos4t.add(post42);
		Troop t4 = new Troop(c4, 10, new Position(6,2), true);
		List<Troop> troops = new ArrayList<Troop>();
		troops.add(t);
		troops.add(t1);
		troops.add(t2);
		troops.add(t3);
		troops.add(t4);
		Map map = new Map(troops);
		t.setMap(map);
		t1.setMap(map);
		t2.setMap(map);
		t3.setMap(map);
		t4.setMap(map);

		t.setAvailablePositions(new AStarPathFinder(map, t.getCreature().getMovement()).findPathAll(map, t.getCurrentPositionX(),t.getCurrentPositionY()));
		t1.setAvailablePositions(new AStarPathFinder(map, t1.getCreature().getMovement()).findPathAll(map, t1.getCurrentPositionX(),t1.getCurrentPositionY()));
		t2.setAvailablePositions(new AStarPathFinder(map, t2.getCreature().getMovement()).findPathAll(map, t2.getCurrentPositionX(),t2.getCurrentPositionY()));
		t3.setAvailablePositions(new AStarPathFinder(map, t3.getCreature().getMovement()).findPathAll(map, t3.getCurrentPositionX(),t3.getCurrentPositionY()));
		t4.setAvailablePositions(new AStarPathFinder(map, t4.getCreature().getMovement()).findPathAll(map, t4.getCurrentPositionX(),t4.getCurrentPositionY()));

		
		kieSession.insert(t);
		kieSession.insert(t1);
		kieSession.insert(t2);
		kieSession.insert(t3);
		kieSession.insert(t4);
		kieSession.getAgenda().getAgendaGroup("movement").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
	}
}
