package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
				RepresentacionTest.class,
				EventoTest.class,
				UsuarioRegistradoTest.class,
				TheaterfyTest.class,
				CicloTest.class
				})

public class AllTests {
	
}