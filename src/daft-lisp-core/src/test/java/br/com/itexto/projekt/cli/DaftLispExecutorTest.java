package br.com.itexto.projekt.cli;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.junit.Test;

public class DaftLispExecutorTest {


	
	@Test
	public void testLoadingScriptsFromClasspath() {
		DaftLispExecutor executor = new DaftLispExecutor();
		assertEquals(System.getenv("DAFT_LISP_HOME") == null, executor.loadingScriptsFromClasspath());
	}
	
	@Test
	public void testExecuteFromClassPath() throws Throwable {
		DaftLispExecutor executor = new DaftLispExecutor();
		if (System.getProperty("DAFT_LISP_HOME") != null) {
			System.getProperty("DAFT_LISP_HOME", null);
		}
		assertTrue(executor.loadingScriptsFromClasspath());
		String[] parameters = {"testScript", "1", "2"};
		Object result = executor.execute(parameters);
		assertNotNull(result);
		assertEquals(3l, result);
	}
	
	@Test
	public void testExecuteFromDaftLispHome() throws Exception {
		
		File tmpDir = new File("/tmp/daftlisp/scripts");
		if (! tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		InputStream input = getClass().getClassLoader().getResourceAsStream("daftlisp/scripts/testScript.clj");
		FileOutputStream fos = new FileOutputStream(new File("/tmp/daftlisp/scripts/testScript.clj"));
		int c = -1;
		while ((c = input.read()) != -1) {
			fos.write(c);
		}
		fos.close();
		input.close();
		DaftLispExecutor executor = new DaftLispExecutor();
		System.setProperty("DAFT_LISP_HOME", "/tmp/daftLisp");
		assertFalse(executor.loadingScriptsFromClasspath());
		String[] parameters = {"testScript", "1", "2"};
		Object result = executor.execute(parameters);
		assertNotNull(result);
		assertEquals(3l, result);
	}

}
