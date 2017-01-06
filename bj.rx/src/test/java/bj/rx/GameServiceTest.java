package bj.rx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.kittu.service.BJGameService;

public class GameServiceTest {
	
	@Before
	public void setUp(){
		
	}

	@Test
	public void playGameOnConsole(){
		 BJGameService gameService = new BJGameService();
		 gameService.playGameOnConsole();
	}
	
  	@After
	public void tearDown(){
		
	}
}
