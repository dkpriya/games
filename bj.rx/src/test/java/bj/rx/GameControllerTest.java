package bj.rx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.kittu.controller.BJGameController;
import com.kittu.model.Game;
 
public class GameControllerTest {
	
	@Before
	public void setUp(){
		
	}

  	@Test
	public void playGame(){
		BJGameController controller = new BJGameController();
		controller.setGame(new Game());
		ModelMap map = new ModelMap();
		controller.start(map);
 		map.forEach((k,v)-> System.out.println(k+": "+v));
		
	}
	
	@After
	public void tearDown(){
		
	}
}
